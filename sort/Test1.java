package com.sort;

/**
 * Created by Administrator on 2017/7/22.
 */
public class Test1 {
    public static void main(String[] args) {
        int a[]={2,7,1,5,3,5,9,4,0};
        System.out.println("排序之前：");
        for (int num:a){
            System.out.print(num+" ");
        }
        //selectSort(a);//1.选择排序(每次循环找出最小的那个元素并将其放到前面)
        //insertSort(a);//2.插入排序(从第一个元素开始放进有序队列，依次遍历将后面的数据依次放到有序队列)
        //bubbleSort(a);//3.冒泡排序(从第一个元素开始，两两相邻的比较，大的往后移，放到n的位置，然后在对剩下的n-1个元素继续排序)
        //quickSort(a,0,a.length-1);//4快速排序(选最左边的元素作为基准元素，先从又向左遍历，找到比基准元素小的数放到最左边，
        // 然后从左向右遍历找到比基准元素大的数放到最右边，不停循环，直至基准元素左边元素)
        //HeapSort(a);//5.堆排序(首先将数组调整成最大二叉堆，然后交换parent和最后节点，输出最大值，剩下n-1个数，
        // 再次调整为最大二叉堆，然后继续)
        //MergeSort(a,0,a.length-1);6.归并排序(首先利用递归的方法将数组分割成很多个小数组，然后归并这些数组，归并过程中排序)
        shellSort(a);//7.希尔排序（首先从第0个元素开始将数组中间距为length/2的元素两两排序，然后将间距减半，继续分组排列，直至间距为1）
        System.out.println();
        System.out.println("排序之后：");
        for (int num:a){
            System.out.print(num+" ");

        }
    }
    //1.选择排序
    public static void selectSort(int a[]){
        for (int i=0;i<a.length;i++){
            int min=i;//最小元素的索引
            for (int j=min+1;j<a.length;j++){
                if (a[min]>a[j]){
                    min=j;
                }
            }
            //内层循环结束，找到最小值并使得min指向它
            if (i!=min){
                int tmp=a[i];
                a[i]=a[min];
                a[min]=tmp;
            }

        }
    }
    //2.插入排序
    public static void insertSort(int a[]){
        if(a!=null){
            //因为第一个数已经放到有序队列里面了，所以从i=1开始排序
            for (int i=1;i<a.length;i++){
                int j;
                int temp=a[i];
                for (j=i-1;j>=0&&temp<a[j];j--){
                    a[j+1]=a[j];
                }
                a[j+1]=temp;
            }
        }
    }
    //3.冒泡排序
    public static void bubbleSort(int a[]){
        if (a!=null){
            //因为只需要比较到倒数第二个元素，所以取length-1
            for (int i=0;i<a.length-1;i++){
                //每成功排序一次，就可以使得下一次要排序的数量减1
                for (int j=0;j<a.length-i-1;j++){
                    if (a[j]>a[j+1]){
                        int tmp=a[j];
                        a[j]=a[j+1];
                        a[j+1]=tmp;
                    }
                }
            }
        }
    }
    //4快速排序
    public static void quickSort(int a[],int left,int right){
        if(left<right){
            int l,r,base;
            l=left;
            r=right;
            base=a[l];//把数组的最左边元素当做基准元素
            while (l<r){
                while (l<r&&a[r]>=base){
                    r--;
                }
                a[l]=a[r];
                while (l<r&&a[l]<=base){
                    l++;
                }
                a[r]=a[l];
            }
            //因为此时l=r所以用哪个都可以，此时将中间位置换为base，base左边比它小，右边比它大
            a[l]=base;
            //以base为边界，递归方法排列它左边和右边的数
            quickSort(a,left,l-1);
            quickSort(a,l+1,right);
        }
    }
    //5.堆排序
    public static void HeapAdjust(int a[],int parent,int length){
        int temp=a[parent];//temp保存当前parent节点，用于后面的交换
        int child=parent*2+1;
        while (child<length){
            if(child+1<length&&a[child+1]>a[child]){
                child++;
            }
            if(temp>=a[child]){
                break;
            }
            a[parent]=a[child];//把孩子节点的值赋给父节点
            //继续向下遍历
            parent=child;
            child=child*2+1;
        }
        a[parent]=temp;//将原来父节点的赋值给原来的子节点
    }
    public static void HeapSort(int a[]){
        // 从(n/2) --> 0逐次遍历,若父节点小于子节点，则交换。遍历之后，得到的数组实际上是一个(最大)二叉堆。
        for (int i=a.length/2;i>=0;i--){
            HeapAdjust(a,i,a.length-1);
        }
        //进行n-1次循环
        for (int i=a.length-1;i>0;i--){
            // 交换a[0]和a[i]。交换后，a[i]是a[0...i]中最大的
            int temp=a[i];
            a[i]=a[0];
            a[0]=temp;
            //调整a[0...i-1]，使得a[0...i-1]仍然是一个最大堆。
            HeapAdjust(a,0,i-1);
        }
    }
    //6归并排序
    public static void MergeSort(int a[],int left,int right){
        int mid=(left+right)/2;
        if(left<right){
            //用递归的方法，排序左半数组
            MergeSort(a,left,mid);
            //用递归的方法，排序右半数组
            MergeSort(a,mid+1,right);
            //左右归并
            merge(a,left,mid,right);
        }
    }
    public static void merge(int a[],int left,int mid,int right){
        int temp[]=new int[right-left+1];//新建一个临时数组，用来存放已经排序好的数
        int i=left;
        int j=mid+1;
        int k=0;
        //先把数组中较小的数字放到临时数组中（条件1）
        while (i<=mid&&j<=right){
            if (a[i]<a[j]){
                temp[k++]=a[i++];
            }else {
                temp[k++]=a[j++];
            }
        }
        //如果右边数组已经都放到临时数组中，这时上面的条件1不能满足了，则将剩下的左边的数组元素全部放到临时数组中
        while (i<=mid){
            temp[k++]=a[i++];
        }
        //如果左边数组已经都放到临时数组中，这时上面的条件1不能满足了，则将剩下的右边的数组元素全部放到临时数组中
        while (j<=right){
            temp[k++]=a[j++];
        }
        //临时数组中的数据覆盖原来的数组
        for (int l=0;l<k;l++){
            a[left+l]=temp[l];
        }
    }
    //7.希尔排序(不稳定算法，因为有可能会把相同元素互换)
    public static void shellSort(int a[]){
        int i,j,temp;
        //步长一开始为数组长度的一半，向下取整，以后每次减半
        for (int gap=a.length/2;gap>=1;gap/=2){
            for (i=gap;i<a.length;i++){
                temp=a[i];//临时变量
                for (j=i-gap;j>=0;j-=gap){
                    if (temp<a[j]){
                        a[j+gap]=a[j];
                        a[j]=temp;
                    }else {
                        break;
                    }
                }
            }
        }
    }
}
