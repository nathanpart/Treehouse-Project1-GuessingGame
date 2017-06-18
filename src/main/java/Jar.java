import java.util.Random;

class Jar {
  private String itemName;
  private int maxItemCount;
  private int itemCount;
  
  public Jar(String itemName, int maxItemCount) {
    this.itemName = itemName;
    this.maxItemCount = maxItemCount;
  }
  
  public void fillJar() {
    Random random = new Random();
    //nextInt returns a random number from 0 to maxItemCount-1, so we add
    // 1 to adjust it to be 1 to maxItemCount
    itemCount =  random.nextInt(maxItemCount) + 1;
  }
  
  public int getCount() {
    return itemCount;
  }
  
  public int getMaxCount() {
    return maxItemCount;
  }
  
  public String getItemName() {
    return itemName;
  }
  
  
}