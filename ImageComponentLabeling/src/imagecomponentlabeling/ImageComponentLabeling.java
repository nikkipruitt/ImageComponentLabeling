/*
 * 
 */

package imagecomponentlabeling;

import java.util.*;

/**
 *
 * @author Nikki
 */
public class ImageComponentLabeling {
    
   public static void main(String [] args)
   {
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
       
       System.out.println("Unlabeled image: ");
       printImage(unlabeledImage);
       System.out.println("\n \n \n");
       
       imageLabeler(unlabeledImage);
   }
   
   private static Queue<String> components = new LinkedList<String>();
   private static int group = 2;
   
   public static void imageLabeler(int[][] image) {
       
       for(int i = 0; i < image.length; i++) {
           for(int j = 0; j < image.length; j++) {
               if(image[i][j] == 1) {
                   String value = i + "," + j;
               
                   components.add(value);
                   image[i][j] = group;
               
                   while(!components.isEmpty()) {
                       int x = Integer.parseInt(components.peek().substring(0,1));
                       int y = Integer.parseInt(components.peek().substring(2));
                   
                       checkNeighbors(image, x, y);
                   
                       components.remove();
                   }
                   
                   group++;
               }
           }
       }
       
       System.out.println("Labeled image: ");
       printImage(image);
   }
   
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
