/*
 * 
 */

package imagecomponentlabeling;

import java.util.*;

public class ImageComponentLabeling {
    
   public static void main(String [] args)
   {
       //create the image grid with unlabeled components
       int[][] unlabeledImage = new int[][]{
           {0, 0, 0, 0, 0, 0, 0, 0, 0},
           {0, 0, 0, 1, 0, 0, 0, 0, 0},
           {0, 0, 0, 1, 1, 0, 0, 0, 0},
           {0, 0, 0, 0, 0, 1, 0, 0, 0},
           {0, 0, 0, 0, 1, 1, 0, 0, 0},
           {0, 0, 1, 0, 0, 1, 0, 1, 0},
           {0, 1, 1, 1, 0, 0, 0, 1, 0},
           {0, 1, 1, 1, 0, 0, 1, 1, 0},
           {0, 0, 0, 0, 0, 0, 0, 0, 0}
       };
       
       //print out the unlabeled image
       System.out.println("Unlabeled image: ");
       printImage(unlabeledImage);
       System.out.println("\n \n \n");
       
       imageLabeler(unlabeledImage);
   }
   
   //create a queue to hold the components to be labeled and a group counter
   private static Queue<String> components = new LinkedList<String>();
   private static int group = 2;
   
   //goes through the grid to label the image components
   public static void imageLabeler(int[][] image) {
       
       for(int i = 0; i < image.length; i++) {
           for(int j = 0; j < image.length; j++) {
               if(image[i][j] == 1) {
                   String value = i + "," + j;
               
                   components.add(value);
                   image[i][j] = group;
               
                   //if the queue is not empty, check surrounding components
                   while(!components.isEmpty()) {
                       int x = Integer.parseInt(components.peek().substring(0,1));
                       int y = Integer.parseInt(components.peek().substring(2));
                   
                       checkNeighbors(image, x, y);
                   
                       //remove the labeled component from the queue
                       components.remove();
                   }
                   
                   group++;
               }
           }
       }
       
       //print the labeled components
       System.out.println("Labeled image: ");
       printImage(image);
   }
   
   //check to see if surrounding components need to be labeled
   private static void checkNeighbors(int[][] image, int x, int y) {
       
       if(image[x - 1][y] == 1) {
           String up = (x - 1) + " " + y;
           components.add(up);
           image[x - 1][y] = group;
       }
       
       if(image[x + 1][y] == 1) {
           String down = (x + 1) + " " + y;
           components.add(down);
           image[x + 1][y] = group;
       }
       
       if(image[x][y - 1] == 1) {
           String left = x + " " + (y - 1);
           components.add(left);
           image[x][y - 1] = group;
       }
       
       if(image[x][y + 1] == 1) {
           String right = x + " " + (y + 1);
           components.add(right);
           image[x][y + 1] = group;
       }
       
   }
   
   //print the image
   private static void printImage(int[][] image) {
       for(int i = 0; i < image.length; i++) {
           if(i != 0 && i != 8) {
               System.out.println();
               for(int j = 0; j < image.length; j++) {
                   if(j != 0 && j != 8) {
                   System.out.print(image[i][j] + " ");
                   }
               } 
           }
       }
   }
}
