package com.linkedlist;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/7/21.
 * 单词循环查找倒数第k个元素
 */
public class Test2 {
    public static void main(String[] args) {
        MyLinkedList list=new MyLinkedList();
        list.addNode(1);
        list.addNode(2);
        list.addNode(3);
        list.addNode(3);
        list.addNode(5);
        //find1(list.head,1);//1.查找正数第k个元素
        //Node d=find(list.head,2);System.out.println(d.data);//2.查找倒数第k个元素
        //Node head=ReverseIteratively(list.head);list.printList(head);//3.1反转链表（非递归方法）
        //Node head=printListReversely(list.head);list.printList(head);//3.2从尾到头输出链表(递归方法实现)
        //delete(list.head);list.printList();
        //System.out.println(SearchMid(list.head).data);//5寻找单链表的中间节点
        //deleteNode(list.head.next);list.printList();////8在不知道头结点的情况下删除指定节点
        /*
        MyLinkedList list2=new MyLinkedList();
        list2.addNode(2);
        list2.addNode(2);
        list2.addNode(1);
        list2.addNode(4);
        list2.addNode(4);
        list2.addNode(5);
        System.out.println(getFirstMeetNode(list.head,list2.head));//9.如何判断两个链表是否相交,如果相交，找到相交的第一个节点
        */

    }
    //1.查找正数第k个元素
    public static void find1(Node head,int k){
        if (k<0){
            System.out.println("error");;
        }
        Node p=head;
        for(int i=0;i<k-1;i++){
            p=p.next;
        }
        System.out.println(p.data);
    }
    //2.查找倒数第k个元素
    public static Node find(Node head,int k){
        if (k<1){
            return null;
        }
        Node p1=head;
        Node p2=head;
        //因为链表是从第0个开始计算，
        for (int i=0;i<k;i++){
            p2=p2.next;
        }
        while (p2!=null){
            p1=p1.next;
            p2=p2.next;
        }
        return p1;
    }
    //3.1反转链表（非递归方法）
    //每移动一个节点都会把当前节点放在链表最前端
    public static Node ReverseIteratively(Node head){
        if (head==null||head.next==null){
            return head;
        }
        Node reHead=null;//翻转后的新链表指针
        Node cur=head;
        while (cur!=null){
            Node pre=cur;
            cur=cur.next;
            pre.next=reHead;
            reHead=pre;
        }
        return reHead;
    }
    //3.2从尾到头输出链表(递归方法实现)
    public static Node printListReversely(Node head){
        if (head==null||head.next==null){
            return head;
        }
        Node reHead=printListReversely(head.next);
        //此时可以kaol为除了head以外后面所有的节点都已经翻转好了
        head.next.next=head;
        head.next=null;
        return reHead;
    }
    //4.删除相同元素
    public static void delete(Node head){
        Node p=head;
        while (p!=null){
            Node q=p;
            while (q.next!=null){
                if (p.data==q.next.data){
                    p.next=q.next.next;
                }else {
                    p.next=q.next;
                }
            }
        }
        p=p.next;
    }
    //5寻找单链表的中间节点
    public static Node SearchMid(Node head){
        Node p=head;
        Node q=head;
        while (q!=null&&q.next!=null&&q.next.next!=null){
            q=q.next.next;
            p=p.next;
        }
        return p;
    }
    //6.判断单链表是否有环
    public static Boolean isLoop(Node head){
        Node fast=head;
        Node slow=head;
        if(fast==null){
            return false;
        }
        while (fast!=null&&fast.next!=null&&fast.next.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if (fast==slow){
                return true;
            }
        }
        return !(fast==null||fast.next==null);
    }
    //7找到单链表环的入口节点
    public static Node FindLoopPort(Node head){
        Node fast=head;
        Node slow=head;
        while (fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if (fast==slow){
                break;
            }
        }
        if (fast==null||fast.next==null){
            return null;
        }
        slow=head;
        while (slow!=fast){
            fast=fast.next;
            slow=slow.next;
        }
        return slow;
    }
    //8在不知道头结点的情况下删除指定节点
    public static boolean deleteNode(Node n){
        //如果要删除的节点为链表尾节点，则无法删除
        if (n==null|| n.next==null){
            return false;
        }
        int tmp=n.data;
        n.data=n.next.data;
        n.next.data=tmp;
        n.next=n.next.next;
        return true;
    }
    //9.如何判断两个链表是否相交,如果相交，找到相交的第一个节点
    public static int getFirstMeetNode(Node h1,Node h2){
        if (h1==null||h2==null){
            return -1;
        }
        Node tail1=h1;
        int len1=1;
        while (tail1.next!=null){
            tail1=tail1.next;
            len1++;
        }
        Node tail2=h2;
        int len2=1;
        while (tail2.next!=null){
            tail2=tail2.next;
            len2++;
        }
        if (tail1.data!=tail2.data){
            return -1;
        }
        Node t1=h1;
        Node t2=h2;
        if (len1>len2){
            int d=len1-len2;
            while (d!=0){
                t1=t1.next;
                d--;
            }
        }
        else{
            int d=len2-len1;
            while (d!=0){
                t2=t2.next;
                d--;
            }
        }
        while (t2.data!=t1.data){
            t1=t1.next;
            t2=t2.next;
        }
        return t1.data;
    }

}
