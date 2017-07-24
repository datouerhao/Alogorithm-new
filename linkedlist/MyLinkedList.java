package com.linkedlist;

/**
 * Created by Administrator on 2017/7/21.
 */
public class MyLinkedList {
    Node head=null;
    public void addNode(int d){
        Node node=new Node(d);
        if (head==null){
            head=node;
            return;
        }
        Node temp=head;
        while (temp.next!=null){
            temp=temp.next;
        }
        temp.next=node;
    }
    public void printList(){
        Node temp=head;
        while (temp!=null){
            System.out.println(temp.data);
            temp=temp.next;
        }
    }
    public void printList(Node head){
        while (head!=null){
            System.out.println(head.data);
            head=head.next;
        }
    }
    public int length(){
        int length=0;
        Node temp=head;
        while (temp!=null){
            length++;
            temp=temp.next;
        }
        return length;
    }
}
