import java.util.LinkedList;

public class StateData {
    String stateName;
     LinkedList<CropData> cropDataLinkedList;
     Stack countingStack;


    public StateData(String stateName) {
        this.stateName=stateName;
        this.cropDataLinkedList=new LinkedList<>();
        this.countingStack=new Stack();

    }

        }

