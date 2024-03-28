import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {

    public static String fileName="D:\\3RD SEMESTER\\DSA\\Example.csv";
    static String  stateName="Andaman and Nicobar Islands";
    static Queue queuebase=new Queue();
    public static void main(String[] args) throws IOException {
int count=1;
           LinkedList<StateData> stateDataLinkedList=readCsv();
//        System.out.println("Overall Data");
//           for (StateData state:stateDataLinkedList){
//              for (CropData cropData:state.cropDataLinkedList){
////                  System.out.println("Name :"+cropData.cropName+"\t\t\t District :"+cropData.district);
//                  count++;
//                  System.out.println("No: "+count+" -> " +cropData.state +" year: "+cropData.cropYear);
//              }
//          }

//        System.out.println("Crop data for specific state:"+stateName);
//          queuebase.forwardTraversing();

//        System.out.println("Stack");
//         for (StateData stateData:stateDataLinkedList) {
//             stateData.countingStack.sortStack();
//             System.out.println("State name: " + stateData.stateName);
//             stateData.countingStack.displayStack();
//         }

        for (StateData stateData:stateDataLinkedList) {
            if (stateData.stateName.equalsIgnoreCase(stateName)){
                 recentOldestCrop(stateData);
                 break;
            }
        }




//        System.out.println(" On year Basis\n\n\n\n\n\n");
//
//
////        2-problem -> Which state is popular for which type of crop?
////        popularState(stateDataLinkedList);
//
//        //Problem 1: How to find the most popular crop in a particular year.
//        popularStateInParticularYear(stateDataLinkedList,2010);










    }

    public static LinkedList<StateData> readCsv() throws IOException {
        int count=1;
        BufferedReader reader=new BufferedReader(new FileReader(fileName));
        LinkedList<StateData> stateDataLinkedList=new LinkedList<>();
        HashMap<String, StateData> stateDataMap = new HashMap<>();

        String line = "";
        String deleimeter = ",";
        reader.readLine();

        while ((line = reader.readLine()) != null) {
            String[]  data = line.split(deleimeter);

            String state = data[0].trim();
            String cropName = data[4].trim();
            String district = data[1].trim();
            int cropYear =(Integer.parseInt(data[2].trim()));

            StateData statedata = stateDataMap.get(state);
            if(statedata==null){
                statedata=new StateData(state);
                stateDataLinkedList.add(statedata);
                stateDataMap.put(state,statedata);
            }
            CropData cropData=new CropData(state,cropName,district,cropYear);
            if (cropData.cropYear == 1997 && state.equalsIgnoreCase(stateName) ){
                queuebase.addObjects(cropData);
            }
            statedata.cropDataLinkedList.add(cropData);
            populateStack(cropName,statedata.countingStack);
        }
        return stateDataLinkedList;
    }


    public static void populateStack(String cropName, Stack countingStack){
    Stack tempcountingStack=new Stack();
    boolean cropFound = false;
    while (!countingStack.isEmpty()){
        Node cropCount=countingStack.popTop();
        if (cropCount.cropName.equalsIgnoreCase(cropName)){
            cropCount.count++;
            cropFound = true;
        }
        tempcountingStack.push(cropCount);
    }
       if (!cropFound) {
           tempcountingStack.push(new Node(cropName,1));
       }

while(!tempcountingStack.isEmpty()){
    countingStack.push(tempcountingStack.popTop());
}
            }

    public static void popularState(LinkedList<StateData> statelist) {
        for (StateData stateData:statelist) {
            Stack stack=stateData.countingStack;
            stack.sortStack();
            System.out.println("State name: " + stateData.stateName);
            stack.displayStack();
            Node poped=stack.popBottom();
            int mostcount=poped.count;
            int count=mostcount;
            System.out.println("\t\t\t\t\t State popular for following crops: ");
            while (count==mostcount){
                System.out.println(poped.cropName+"\t"+poped.count);
                poped = stack.popBottom();
                count=poped.count;
            }
        }
    }

    public static void popularStateInParticularYear(LinkedList<StateData> statelist,int year) {
        Stack tempStack = new Stack();
        for (StateData stateData : statelist) {
            LinkedList<CropData> cropDataList = stateData.cropDataLinkedList;
            while (!cropDataList.isEmpty()) {
                CropData cropData = cropDataList.pop();
                if (cropData.cropYear == year) {
                    populateStack(cropData.cropName, tempStack);
                }
            }
            tempStack.sortStack();
            System.out.println("State name: " + stateData.stateName);
            tempStack.displayStack();
            Node poped=tempStack.popBottom();
            int mostcount=poped.count;
            int count=mostcount;
            System.out.println("\t\t\t\t\t State: "+stateData.stateName +"\t Year: "+year);
            while (count==mostcount){
                System.out.println(poped.cropName+"\t"+poped.count);
                poped = tempStack.popBottom();
                count=poped.count;
            }
        }
    }
       public static void recentOldestCrop(StateData state){
          int oldestYear=0, recentYear=0;

          for (CropData crop:state.cropDataLinkedList){
              if (oldestYear==0){
                  oldestYear=crop.cropYear;
              }
              if (recentYear==0){
                  recentYear=crop.cropYear;
              }
              if (oldestYear< crop.cropYear){
                  recentYear= crop.cropYear;
              }
          }
           System.out.println("Oldest year: "+oldestYear);
           System.out.println("Recent year: "+recentYear);
           String district=null;
           ArrayList<String> cropoldest=new ArrayList<>();
           ArrayList<String> croprecent=new ArrayList<>();
           boolean status=false;
           for (CropData crop:state.cropDataLinkedList) {
               if (crop.cropYear == oldestYear) {
                   cropoldest.add(crop.cropName);
               }
               if (crop.cropYear == recentYear) {
                   croprecent.add(crop.cropName);
               }

           }

           System.out.println("Oldest: "+ cropoldest);
           System.out.println("Recent: "+croprecent);




    }



















        }             // Main class Ends up here


