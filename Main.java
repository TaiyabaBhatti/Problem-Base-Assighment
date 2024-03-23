import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {

    public static String fileName="D:\\3RD SEMESTER\\DSA\\crop_production.csv";
    static String  stateName="Andhra pradesh";
    static Queue queuebase=new Queue();
    public static void main(String[] args) throws IOException {

           LinkedList<StateData> stateDataLinkedList=readCsv();
          int count=0;
//        System.out.println("Overall Data");
//           for (StateData state:stateDataLinkedList){
//              for (CropData cropData:state.cropDataLinkedList){
////                  System.out.println("Name :"+cropData.cropName+"\t\t\t District :"+cropData.district);
//                  count++;
//                  System.out.println("No: "+count+" -> " +cropData.state);
//              }
//          }

//        System.out.println("Crop data for specific state:"+stateName);
//          queuebase.forwardTraversing();

        System.out.println("Stack");
         for (StateData stateData:stateDataLinkedList){
             stateData.countingStack.sortStack();
             System.out.println("State name: "+stateData.stateName);
             stateData.countingStack.displayStack();
         }
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
        }





























