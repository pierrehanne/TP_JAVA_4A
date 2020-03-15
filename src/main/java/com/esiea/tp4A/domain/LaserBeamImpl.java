package com.esiea.tp4A.domain;

public class LaserBeamImpl {

    private Position currentPosition;
    private Direction currentDirection;
    private boolean destroyed;
    private int range;
    private int distanceTravelled;

    LaserBeamImpl(int x, int y, Direction direction) {
        currentPosition = Position.of(x, y, direction);
        destroyed = false;
        distanceTravelled=1;
        range = initRange();
    }

    public Position getCurrentPosition(){
        return currentPosition;
    }
    public Boolean getDestroyed() { return destroyed; }
    public int getRange() { return range; }

    private int initRange(){
        int alea = (int) (Math.random() * 10);
        if(alea<6) { range = 30;} // faible portée
        if(alea==10) { range = 10000;} //portée illimitée (réfléchir à un moyen de représenter l'infini de manière plus "propre")
        else{ range = 60;} // portée moyenne
        return range;
    }


    //ajoute la valeur passée en paramètres à la case aux coordonnées spécifiées
    public void laserMapLimit(){
        int x = getCurrentPosition().getX();
        int y = getCurrentPosition().getY();
        if(x==51) { this.currentPosition = Position.of(-48, y, currentDirection); System.out.println("x=51");
            System.out.println("In laserMapLimit : Position map:" + this.getCurrentPosition().getX() + "," + this.getCurrentPosition().getY());
        }
        if(y==51) { this.currentPosition = Position.of(x, -48, currentDirection); System.out.println("y=51"); }
        if(x==-50) { this.currentPosition = Position.of(48, y, currentDirection); System.out.println("x=-50"); }
        if(y==-50) { this.currentPosition = Position.of(x, 48, currentDirection); System.out.println("y=-50"); }
    }

    public Position move (PlanetMapImpl planetMap){
        currentDirection = currentPosition.getDirection();
        if(!destroyed){
            //planetMap.MajMap(currentPosition.getX(), currentPosition.getY(), 0);
            if(currentDirection == Direction.SOUTH) currentPosition = Position.of(currentPosition.getX(), currentPosition.getY()-1, currentDirection);
            if(currentDirection == Direction.WEST) currentPosition = Position.of(currentPosition.getX()-1, currentPosition.getY(), currentDirection);
            if(currentDirection == Direction.NORTH) currentPosition = Position.of(currentPosition.getX(), currentPosition.getY()+1, currentDirection);
            if(currentDirection == Direction.EAST) currentPosition = Position.of(currentPosition.getX()+1, currentPosition.getY(), currentDirection);
            distanceTravelled++;

            System.out.println("Position map:" + this.getCurrentPosition().getX() + "," + this.getCurrentPosition().getY());
            laserMapLimit();
        }
        obstacleCollisionCheck(planetMap);
        rangeCheck();

        if(destroyed){ return null; }
        else{ return currentPosition;}
    }

    //si le laser rencontre un obstacle, ces deux éléments sont détruits
    public void obstacleCollisionCheck(PlanetMapImpl planetMap){
        if(planetMap.getInfo(currentPosition.getX(), currentPosition.getY())==1){
            planetMap.setMapSquare(currentPosition.getX(), currentPosition.getY(), 0);
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
