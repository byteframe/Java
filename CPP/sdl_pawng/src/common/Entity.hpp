#ifndef __ENTITY_HPP_
#define __ENTITY_HPP_

class Entity
{
    int x;
    int y;
public:
    Entity();    Entity(int in_x, int in_y);
    int getx() { return x; }
    int gety() { return y; }
    void setx(int in_x) { x = in_x; }
    void sety(int in_y) { y = in_y; }
};

#endif
