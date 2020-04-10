package com.esiea.tp4A.domain;

public class LaserBeamImpl {

    private Position currentPosition;
    private final Direction currentDirection;
    private boolean destroyed;
    private final int range;
    private int distanceTravelled;

    LaserBeamImpl(int x, int y, Direction direction) {
        currentPosition = Position.of(x, y, direction);
        currentDirection = direction;
        destroyed = false;
        distanceTravelled=1;
        range = initRange();
    }

    public Position getCurrentPosition(){
        return currentPosition;
    }
    public Boolean getDestroyed() { return destroyed; }
    public int getRange() { return range; }

    public void setDistanceTravelled(int distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }

    private int initRange(){
        int range;
        int alea = (int) (Math.random() * 10);
        if(alea<6) { range = 30;} // faible portée
        if(alea==10) { range = 10000;} //portée illimitée (réfléchir à un moyen de représenter l'infini de manière plus "propre")
        else{ range = 60;} // portée moyenne
        return range;
    }

    /* //POUR L'INSTANT INUTILISEE
    //ajoute la valeur passée en paramètres à la case aux coordonnées spécifiées
    public void laserMapLimit(){
        Position currentPosition = Position.of(0,0, Direction.NORTH);
        int x = getCurrentPosition().getX();
        int y = getCurrentPosition().getY();
        if(x==51) { currentPosition = Position.of(-48, y, currentDirection); System.out.println("x=51");
            System.out.println("In laserMapLimit : Position map:" + this.getCurrentPosition().getX() + "," + this.getCurrentPosition().getY());
        }
        if(y==51) { currentPosition = Position.of(x, -48, currentDirection); System.out.println("y=51"); }
        if(x==-50) { currentPosition = Position.of(48, y, currentDirection); System.out.println("x=-50"); }
        if(y==-50) { currentPosition = Position.of(x, 48, currentDirection); System.out.println("y=-50"); }
        this.currentPosition = currentPosition;
    }*/

    public Position move (PlanetMapImpl planetMap){
        if(!destroyed){ currentPosition = currentPosition.forward1();
                        distanceTravelled++;
                        System.out.println("Position map:" + this.getCurrentPosition().getX() + "," + this.getCurrentPosition().getY()); }
        obstacleCollisionCheck(planetMap);
        rangeCheck();
        if(destroyed){ return null; }
        else{ return currentPosition;}
    }

    //si le laser rencontre un obstacle, ces deux éléments sont détruits
    public void obstacleCollisionCheck(PlanetMapImpl planetMap){
        if(planetMap.getInfo(currentPosition.getX(), currentPosition.getY())==1){
            planetMap.MajMap(currentPosition.getX(), currentPosition.getY(), 0);
            destroyed = true;
        }
    }

    //vérifier que le laser n'a pas atteint sa portée maximale
    public void rangeCheck(){
        if(distanceTravelled>range){
            destroyed = true;
        }
    }

}
