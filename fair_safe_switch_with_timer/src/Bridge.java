
class Bridge {

    synchronized void eastCross(EastCar c) throws InterruptedException {}

    synchronized void upEastExited(EastCar c) throws InterruptedException{}

    synchronized void westCross(WestCar c) throws InterruptedException {}

    synchronized void upWestExited(WestCar c) throws InterruptedException{}
}
