package test;

/**
 * @author zhuxiaomeng
 * @date 2017/12/21.
 * @email 154040976@qq.com
 */
public class Test {

  public static void sort(int arr[],int l,int r){
    if(l<r){
      int i=l,j=r,x=arr[i];
      while(i<j){
        while(i<j&&arr[j]>=x)
          j--;
        if(i<j)
          arr[i++]=arr[j];
        while(i<j&&arr[i]<j){
          i++;
        }
        if(i<j){
          arr[j--]=arr[i];
        }
      }
      arr[i]=x;
      sort(arr,l,i-1);
      sort(arr,i+1,r);
    }
  }

  public static void main(String[] args) {
    int[] arr={15,18,45,16};
    sort(arr,0,3);
    System.out.println(arr);
  }

}
