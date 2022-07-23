public class MyArrayList<Type> {
    private Type[] list;
    private int capacity;
    private int size;
    {

        this.capacity=16;
        this.size=0;
        this.list=(Type [])new Object[capacity];
    }


    public void insert(Type item, int index) {

        if(size >= capacity)
            resize();

        if(index >= 0 && index < capacity) {

            for(int i = size; i > index; i--) {
                list[i] = list[i-1];
            }

            list[index] = item;

            size++;
        }
    }

    public Type remove(int index)
    {
        if(index<0 || index>this.size)
            return null;
        Type element=this.get(index);
        for(int i=index;i<this.size-1;i++) this.list[i]=this.list[i+1];
        this.size--;
        return element;
    }
    public boolean contains(Type element)
    {
        return this.indexOf(element)!=-1;
    }
    public int indexOf(Type element)
    {
        if(element==null) return -1;
        for(int i=0;i<this.size;i++) if(element.equals(get(i)))
            return i;
        return -1;
    }
    public Type get(int index)
    {
        if(index<0 || index>this.size)
            return null;
        return this.list[index];

    }
    public void set(int index,Type element)
    {
        if(index<0 || index>this.size)
            return;
        this.list[index]=element;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size==0;
    }
    public String toString()
    {

        if(this.size==0) return "[]";
        String n="[";
        for(int i=0;i<this.size;i++)
        {
            n+=this.get(i);
            if(i!=this.size-1) n+=", ";
        }
        n+="]";
        return n;
    }
    private void resize() {
        if(this.size<this.capacity)
            return;
        capacity*=2;

        Type []newList=(Type []) new Object[capacity];

        for(int i=0;i<this.size;i++) newList[i]=this.list[i];
        this.list=newList;
    }


}