package framework;

public class QueueList <T>
{

    private Node head, foot;
    private int size = 0;

    public QueueList()
    {
        head = foot = null;
    }

    public int getSize()
    {
        return size;
    }

    public void push(T t)
    {
        Node newNode = new Node(t);

        if (head == null)
        {
            head = foot = newNode;
        }
        else
        {
            foot.link = newNode;
            foot = newNode;
        }

        size++;
    }

    public T pop()
    {
        T data = head.data;
        head = head.link;
        size--;

        return data;
    }

    public T get(int index)
    {
        Node current = head;

        if (head == null)
        {
            return null;
        }

        for (int i = 0; i < index; i++)
        {
            current = current.link;
            if (current == null)
            {
                return null;
            }
        }
        return current.data;
    }

    private class Node
    {
        private T data;
        private Node link;

        Node(T value)
        {
            this.data = value;
        }

        public Node()
        {
            this.data = null;
            this.link = null;
        }

        public Node(T newData, Node nextLink)
        {
            this.data = newData;
            this.link = nextLink;
        }
    }
}
