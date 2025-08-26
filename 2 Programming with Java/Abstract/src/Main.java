abstract class Shape {
    public abstract float calculateArea();
    public abstract void draw();
    public abstract float calculatePerimeter();

}
class Rectangle extends Shape{
    private float width;
    private float height;
    Rectangle(float width,float height){
        this.width=width;
        this.height=height;
    }
    @Override
    public float calculateArea(){
        float area=this.width*this.height;
        return area;
    }
    @Override
    public void draw(){
        System.out.println("Drawing Rectangle with width"+this.width+" height+"+this.height);
    }
    @Override
    public float calculatePerimeter(){
        float perimeter=2*(this.width+this.height);
        return perimeter;

    }

}

class Circle extends Shape{
    private float radius;
    Circle(float r){
        this.radius=r;
    }
    @Override
    public float calculateArea(){
        float area=(float) Math.PI * this.radius*this.radius;
        return area;
    }
    @Override
    public void draw(){
        System.out.println("Drawing Circle with radius "+this.radius);
    }
    @Override
    public float calculatePerimeter(){
        return (float) (2* Math.PI * this.radius);

    }
}
