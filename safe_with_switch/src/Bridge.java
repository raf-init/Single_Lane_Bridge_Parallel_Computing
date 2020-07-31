
//gia ti sigkekrimeni class stirihtika ston kodika pou mas dothike kata tin anathesi tis ergasias
class Bridge {

    synchronized void eastCross(EastCar c) throws InterruptedException {}

    synchronized void upEastExited(EastCar c) throws InterruptedException{}

    synchronized void westCross(WestCar c) throws InterruptedException {}

    synchronized void upWestExited(WestCar c) throws InterruptedException{}
}
