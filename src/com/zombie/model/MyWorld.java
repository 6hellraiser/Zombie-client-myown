package com.zombie.model;

import com.badlogic.gdx.math.Vector2;
import com.zombie.PluginFactory;
import com.zombie.enums.BuildingType;
import com.zombie.interconnection.Client;
import com.zombie.interconnection.Entity;
import com.zombie.plugin.*;
import com.zombie.util.GuiCallback;
import com.zombie.view.CameraUtils;

import java.util.ArrayList;
import java.util.Random;

import org.json.simple.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: 123
 * Date: 05.12.13
 * Time: 13:50
 * To change this template use File | Settings | File Templates.
 */
public class MyWorld implements IWorld {

    //
    //private ArrayList<Creature> creatures = new ArrayList<Creature>();

    private ArrayList<AbstractCharacter> men = new ArrayList<AbstractCharacter>();
    private ArrayList<AbstractCharacter> ourMen = new ArrayList<AbstractCharacter>();
    private ArrayList<Creature> creatures = new ArrayList<Creature>();

    private Map  map = new Map(200, 200);
    private Building base;
    private ArrayList<Class> plugins;

    public float getBaseX() {
        return base.getPosition().x;
    }

    public float getBaseY() {
        return base.getPosition().y;
    }

    public int width;
    public int height;

    public ArrayList<AbstractCharacter> getMen() {
        return men;
    }
    public ArrayList<AbstractCharacter> getOurMen() {
        return ourMen;
    }

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public Map getMap() {
        return map;
    }

    public MyWorld() {
        width = 8;
        height = 8;
        createWorld();
    }

    public void createWorld() {
        plugins = PluginFactory.getPlugins();
        /*ArrayList<AbstractCharacter> plugins = PluginFactory.getPlugins();
        for (int i = 0; i < plugins.size(); i++) {
            men.add(plugins.get(i));
        }
        if (plugins.size() >= 1) {
            //men.add(plugins.get(0));

            //Man man = new Man(new Vector2(2,10), Team.Red, this);
            //men.add(man);
            //man.action();
            //man = new Man(new Vector2(14, 12), Team.Yellow, this);
            //men.add(man);
            //man.action();
        }
        else {
            System.out.println("Where is the strategy?");
        } */
        //TODO: PARSE THE UNIVERSE
        //TODO отловить стартгейм, ждать, пока она не придет
        //TODO распарсить энтити, запихнуть вниз
        //TODO сервер должен присылать тим
        //в классе выше ждать, здесь парсить
        //base = new Building(new Vector2(x, y), Team.Red);
        //foreach entity: getEntityInfo from server, parse it and add to the world or map
        //zombie: new Creature(new Vector (x, y), properties);
        //men: all men except ours
        //ourMen: our men, they are in the tail of LIFE sorry json
        //последний параметр - айди зомби, который надо получить от сервера
        //nope. its zombie's properties including... all (loook at AliveObjectProperties

        //попытка распарсить стартгейм
        long team_id = (long) Client.team_id;
        switch ((int) team_id) {
            case 0:
                base = new Building(new Vector2(Client.base_x, Client.base_y), Team.Blue);
                break;
            case 1:
                base = new Building(new Vector2(Client.base_x, Client.base_y), Team.Green);
                break;
            case 2:
                base = new Building(new Vector2(Client.base_x, Client.base_y), Team.Red);
                break;
            case 3:
                base = new Building(new Vector2(Client.base_x, Client.base_y), Team.Yellow);
                break;
        }

        map.getBuildings().add(base);

        final ArrayList<Entity> entities = new ArrayList<Entity>();

        for (int i = 0; i < Client.entities_id.size(); i++) {
           // e = new com.zombie.interconnection.Building();
           // final Long id = Client.entities_id.get(i);
           // final IWorld w = this;
            Client.getEntity(new GuiCallback<Object>() {
                @Override
                protected void call(Object param) {
                    //TODO parse

                    JSONObject parameters = (JSONObject) param;
                    JSONObject entity = (JSONObject) parameters.get("entity");
                    JSONObject position = (JSONObject) entity.get("position");
                    int x = (int)(long)(Long)position.get("x");
                    int y = (int)(long)(Long)position.get("y");
                    String type = (String) parameters.get("type");
                    if (type.equals("building")) {
                        Entity e = new com.zombie.interconnection.Building();
                        e.x = x;
                        e.y = y;
                        e.type = "building";
                        entities.add(e);
                       // map.getBuildings().add(new Building(new Vector2(x, y), BuildingType.Hospital));
                    }
                  /*  x[0] = (int)((long)Client.getEntity_x);
                    y[0] = (int)((long)Client.getEntity_y);
                    if (Client.type.equals("building")) {

                        map.getBuildings().add(new Building(new Vector2(x[0], y[0]), BuildingType.Hospital));
                    } else if ( Client.type.equals("zombie")) {
                        //int health, int stamina, int accuracy, int visibility, int speed, IWorld world, Team team, int id
                        AliveObjectProperties properties = new AliveObjectProperties((int)((long) Client.health), (int)((long)Client.stamina), (int)((long)Client.accuracy),
                                50, (int)((long)Client.speed), w, null, (int)((long)id));
                        Weapon weapon = new Weapon();
                        weapon.setDamage((int)((long)Client.weapon_Damage));
                        Creature creature = new Creature(new Vector2(x[0], y[0]), properties);
                        creature.setWeapon(weapon);
                        creatures.add(creature);
                    } else if (Client.type.equals("man")) {
                        AliveObjectProperties properties = new AliveObjectProperties((int)((long) Client.health), (int)((long)Client.stamina), (int)((long)Client.accuracy),
                                50, (int)((long)Client.speed), w, null, (int)((long)id));
                        switch ((int)((long)Client.team)) {
                            case 0:
                                properties.setTeam(Team.Blue);
                                break;
                            case 1:
                                properties.setTeam(Team.Green);
                                break;
                            case 2:
                                properties.setTeam(Team.Red);
                                break;
                            case 3:
                                properties.setTeam(Team.Yellow);
                                break;
                        }
                        try{
                            AbstractCharacter character = (AbstractCharacter)plugins.get(0).getConstructors()[0].newInstance(properties); //что это
                            character.setX(x[0] *1f);
                            character.setY(y[0] *1f);
                            men.add(character);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (Client.type.equals("water")) {
                        map.getResources().add(new Resource(new Vector2(x[0], y[0]), ResourceType.Water));
                    } else if (Client.type.equals("food")) {
                        map.getResources().add(new Resource(new Vector2(x[0], y[0]), ResourceType.Food));
                    } else if (Client.type.equals("medicines")) {
                        map.getResources().add(new Resource(new Vector2(x[0], y[0]), ResourceType.Medicines));
                    }*/

                }
            }, Client.entities_id.get(i));
        }

        for (int j = 0; j < entities.size(); j++) {
            if (entities.get(j).type.equals("building")) {
                map.getBuildings().add(new Building(new Vector2(entities.get(j).x, entities.get(j).y), BuildingType.Hospital));
            }
        }


      /*  AliveObjectProperties properties = new AliveObjectProperties(50, 50, 50, 50, 50, this, null, 1);
        creatures.add(new FirstZombie(new Vector2(10,2), properties));
        //отослать серверу вектор с координатами и айди зомби
        creatures.add(new SecondZombie(new Vector2(8,2), properties));
        creatures.add(new ThirdZombie(new Vector2(10,4), properties));
        creatures.add(new SecondZombie(new Vector2(14,3), properties));
        creatures.add(new FirstZombie(new Vector2(15,2), properties));
        creatures.add(new ThirdZombie(new Vector2(3,10), properties));
        creatures.add(new SecondZombie(new Vector2(3,14), properties));
        creatures.add(new FirstZombie(new Vector2(6,12), properties));
        creatures.add(new ThirdZombie(new Vector2(12,14), properties));
        creatures.add(new SecondZombie(new Vector2(14,13), properties));
        creatures.add(new FirstZombie(new Vector2(15,12), properties));
        map = new Map(36, 25);
        map.getWater().add(new Vector2(1, 4));
        map.getWater().add(new Vector2(1, 2));
        map.getWater().add(new Vector2(1, 3));
        map.getWater().add(new Vector2(2, 2));
        map.getWater().add(new Vector2(2, 3));
        map.getTeams().add(new Building(new Vector2(0, 0), Team.Yellow));
        map.getBuildings().add(new Building(new Vector2(1, 5), BuildingType.Hospital));
        map.getBuildings().add(new Building(new Vector2(6, 5), BuildingType.Police));
        map.getTeams().add(new Building(new Vector2(7, 10), Team.Red));
        map.getTeams().add(new Building(new Vector2(13, 1), Team.Green));
        //creatures.add(new Creature(new Vector2(10,2)));
        map.getResources().add(new Resource(new Vector2(2, 7), ResourceType.Food));//map size*/
    }

    public ArrayList<AliveObject> getNeighbours(AliveObject a) {
        //TODO отправить ид игрока, получить массив соседей в области видимости
        ArrayList<AliveObject> result = new ArrayList<AliveObject>();
        //гетВизиблЭнтитис, потом выбираешь из них всех зомбаков и людей, заполняешь их поля (для каждого - гетЭнтити, по идее, хотя не знаю даже)

        /*for (int i = 0; i < this.getCreatures().size(); i++) {
            if ((getDistance(a, this.getCreatures().get(i)) <= a.getVisibility()) && (!a.equals(this.getCreatures().get(i)))) {
                result.add(this.getCreatures().get(i));
            }
        }
        for (int i = 0; i < this.getMen().size(); i++) {
            if ((getDistance(a, this.getMen().get(i)) <= a.getVisibility()) && (!a.equals(this.getMen().get(i)))) {
                result.add(this.getMen().get(i));
            }
        }*/
        return result;
    }

    public int getDistance(AliveObject first, AliveObject second) {
        int distance = (int)Math.sqrt((first.getX() - second.getX())*(first.getX() - second.getX()) +
                (first.getY() - second.getY())*(first.getY() - second.getY()));
        return distance;
    }

    public void hit(final AliveObject attacker, final AliveObject victim) {
        //victim.getHealth();
        //attacker.getAccuracy();
        if (checkHit(attacker, victim)) {
            //TODO: hit command
            //нанос урона в сооответствии со способностями, здороье виктима уменьшать на столько-то
            Client.attack(new GuiCallback<Object>() {
                @Override
                protected void call(Object params) {
                    if (Client.damage == -1) {
                        System.out.println("Attack is beside of the mark");
                    }
                    else {
                        //show damage
                    }
                }
            }, attacker.getId(), victim.getId());
            //TODO: mb current - damage? victim.setCurrentHealth(victim.getCurrentHealth() - (attacker.getWeapon().getDamage() + (attacker.getAccuracy() + attacker.getCurrentHealth())/2)/2);
        }
    }

    private final float delta = 0.016f;

    public void moveLeft(final AliveObject obj) {

        if (checkPath(obj, Side.Left)) {
            obj.setxSpeed(-obj.getSpeed() * delta);
            obj.setX(obj.getX() + obj.getxSpeed());
            //TODO: move left command setX
            Client.move(new GuiCallback<Object>() {
                @Override
                protected void call(Object params) {
                    obj.setX((Float)Client.move_x);
                    obj.setY((Float) Client.move_y);
                }
            }, obj.getId(), obj.getX(), obj.getY());
            //move (id, obj.getx,obj.gety) так для всех четырех
            //obj.setY(obj.getY() + obj.getySpeed());
        }
    }
    public void moveRight(final AliveObject obj) {

        if (checkPath(obj, Side.Right)) {
            obj.setxSpeed(obj.getSpeed() * delta);
            obj.setX(obj.getX() + obj.getxSpeed());
            //TODO: move right command setX
            Client.move(new GuiCallback<Object>() {
                @Override
                protected void call(Object params) {
                    obj.setX((Float)Client.move_x);
                    obj.setY((Float)Client.move_y);
                }
            }, obj.getId(), obj.getX(), obj.getY());
        }
    }
    public void moveUp(final AliveObject obj) {

        if (checkPath(obj, Side.Up)) {
            obj.setySpeed(obj.getSpeed() * delta);
            obj.setY(obj.getY() + obj.getySpeed());
            //TODO: move up command setY
            Client.move(new GuiCallback<Object>() {
                @Override
                protected void call(Object params) {
                    obj.setX((Float)Client.move_x);
                    obj.setY((Float)Client.move_y);
                }
            }, obj.getId(), obj.getX(), obj.getY());
        }
    }
    public void moveDown(final AliveObject obj) {

        if (checkPath(obj, Side.Down)) {
            obj.setySpeed(-obj.getSpeed() * delta);
            obj.setY(obj.getY() + obj.getySpeed());
            //TODO: move down command setY
            Client.move(new GuiCallback<Object>() {
                @Override
                protected void call(Object params) {
                    obj.setX((Float)Client.move_x);
                    obj.setY((Float)Client.move_y);
                }
            }, obj.getId(), obj.getX(), obj.getY());
        }
    }
    public void randomMove(AliveObject obj) {
        Random random = new Random();

        int r = random.nextInt(4);
        switch (r) {
            case 0:
                moveLeft(obj);
                break;
            case 1:
                moveRight(obj);
                break;
            case 2:
                moveUp(obj);
                break;
            case 3:
                moveDown(obj);
        }
    }

    //список видимых ресурсов
    public ArrayList<Resource> getVisibleResources(AliveObject a) {
    ArrayList<Resource> resources = new  ArrayList<Resource>();
        //TODO: NEW TODO!!! get visible resources from server
        //сначала гетВизиблЭнтитис, потом из них выбираешь ресурсы
        return resources;
    }

    @Override
    public void takeResource(AliveObject a, com.zombie.plugin.Resource r) {

    }

    //подобрать ресурс
    public void takeResource(AliveObject a, Resource r) {
        //TODO: NEW TODO!!! take selected resource
        //команда take
    }

    //список доступных ресурсов из рюкзака
    public ArrayList<Resource> getAvailableResources(AliveObject a) {
        ArrayList<Resource> resources = new ArrayList<Resource>();
        //TODO: NEW TODO!!! get resources from backpack from server
        //гетЭнтити по а.гетАйди; у него ищешь рюкзак и суёшь сюда все ресурсы из рюкзака
        return resources;
    }

    @Override
    public void useResource(AliveObject a, com.zombie.plugin.Resource r) {

    }

    //использовать ресурс
    public void useResource(AliveObject a, Resource r) {
        //TODO: NEW TODO!!! use selected resource
        //там кажется есть команда юз
    }

    //получить список ресурсов на базе
    public ArrayList<Resource> getBaseResources(AliveObject a) {
        ArrayList<Resource> resources = new ArrayList<Resource>();
        //TODO: NEW TODO!!! get resources from base from server
        //то же самое: гетЭнтити(бейс.гетАйди()), ищешь у неё список ресурсов, суёшь в ресурсес
        return resources;
    }

    private final float size = 1f;

    public boolean checkPath(AliveObject object, Side side) {
        if ((side.equals(Side.Left)) && (object.getX() <= 0)) {
            return false;
        }

        if ((side.equals(Side.Right)) && (object.getX() >= CameraUtils.getCAMERA_WIDTH() - 2*size)) {
            return false;
        }

        if ((side.equals(Side.Up)) && (object.getY() >= CameraUtils.getCAMERA_HEIGHT() - 2*size)) {
            return false;
        }

        if ((side.equals(Side.Down)) && (object.getY() <= 0)) {
            return false;
        }

        ArrayList<Vector2> water = getMap().getWater();

        for (int i = 0; i < water.size(); i++) {
            if ((object.getX() >= water.get(i).x - size)
                    && (object.getX() <= water.get(i).x + size)
                    && (object.getY() >= water.get(i).y - size)
                    && (object.getY() <= water.get(i).y + size)
                    )  {
                if ((side.equals(Side.Right)) && (object.getX() <= water.get(i).x))
                    return false;
                if ((side.equals(Side.Left)) && (object.getX() >= water.get(i).x))
                    return false;
                if ((side.equals(Side.Up)) && (object.getY() <= water.get(i).y))
                    return false;
                if ((side.equals(Side.Down)) && (object.getY() >= water.get(i).y))
                    return false;
            }
        }
        return true;
    }

    public boolean checkHit(AliveObject attacker, AliveObject victim) {
        int distance = getDistance(attacker, victim);
        if (distance <= attacker.getWeapon().getDistance()) {
            return true;
        }
        else {
            return false;
        }
    }

    public Side getSide(AliveObject object, AliveObject neighbour) {
        if ((object == null) || (neighbour == null))
            return null;
        float x = object.getX() - neighbour.getX();
        float y = object.getY() - neighbour.getY();
        if (Math.abs(x) > Math.abs(y)) {
            if (x > 0)
                return Side.Left;
            else
                return Side.Right;
        }
        else {
            if (y > 0)
                return Side.Down;
            else
                return Side.Up;
        }
    }
}
