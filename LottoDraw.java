import java.util.Random; //importing Random type
/*
 * Simulates a lotto draw 
 * 
 * @author Keanu De Cleene
 */
public class LottoDraw {
    
private StrLinkedList madeLottoDraw;
private StrLinkedList winningNumbers;
private double prizeMoneyGiven; //the prize money given form draw
private double totalSales; //the total sales
private final int TICKET_PRICE = 10;
private final int[] PRIZE_AMOUNT = {0, 0, 10, 100, 1000, 10000, 100000};
private final StrLinkedList[] ticketList = {};

/*
 * initialises LottoDraw with the amount of numbers given
 * 
 * @param size of the Lotto range
 */
public LottoDraw(int size){
    madeLottoDraw = new StrLinkedList();
    for(int i = 1; i <= size; i++){
        madeLottoDraw.add(Integer.toString(i));
    }
}

/*
 * selects the winning numbers from madeLottoDraw and puts it into new winningNums str linked list
 *
 */
public void selectWinningNums(){
    Random rand = new Random();
    winningNumbers = new StrLinkedList();
    //select 6 nums
    for(int i = 0; i < 6; i++){
        //gets random string value from the madelotto draw
        int randIndex = rand.nextInt(madeLottoDraw.getLength());
        String randValString = madeLottoDraw.getValueAt(randIndex);
        winningNumbers.add(randValString);
    }
}

/*
 * will check ticket parsed with the winning numbers and see how many match and what the prize reward       
 * is
 * @param ticket to check
 * @param ticketNums number of tickets to check
 * @returns the prize money amount
 */
public int checkTicketPrize(StrLinkedList ticket){
    int ticketLength = ticket.getLength();
    int prize;
    int matchCount = 0;
    //for the length of the ticket
    for(int i = 0; i < ticketLength; i++){
        String ticketLetter = ticket.getValueAt(i);
        if(winningNumbers.hasValue(ticketLetter)){
            matchCount++;
        }
    }
    //maps the count index to the prize_amount array for the corresponding prize
    prize = PRIZE_AMOUNT[matchCount];
    return prize;
}

/*
 * Adds to global scope prize money given
 * 
 * @param ticket that needs to be added
 */
public void calctotalPrizeMoney(StrLinkedList ticket){
    prizeMoneyGiven += checkTicketPrize(ticket);;
}

/*
 * adds to the global scope calcearnings variable with the ticket price
 */
public void calcEarnings(){
    totalSales += TICKET_PRICE;
}

/*
 * calculates the total profit from all the ticket sales and prizes given
 * 
 * @returns the profit amount 
 */
public double calcProfit(){
    double profit = totalSales - prizeMoneyGiven;
    return profit;
}

/*
 * creates all of the tickets to be checked plus prints there prizes as well as the total prize money,
 * as well as the total sales from the lottery tickets and the fundraising profit that would be made
 * 
 * @param numTicketsTotal number of tickets to generate
 * @param ticketNums amount of numbers each ticket holds
 */
public void generateLotteryTickets(int numTicketsTotal,int ticketNums){
    //FOR each ticket to make
    for(int i = 0; i < numTicketsTotal; i++){
        StrLinkedList ticket = new StrLinkedList();
        Random rand = new Random();
        //FOR the amount of numbers in ticket
        for(int j = 0; j < ticketNums; j++){
            //getting random string value from the made lotto
            int randIndex = rand.nextInt(madeLottoDraw.getLength());
            String randValString = madeLottoDraw.getValueAt(randIndex);
            //ensures the ticket numbers are not repeating
            if(!ticket.hasValue(randValString)){
                ticket.add(randValString);
            }
            else{
                j += -1;
            }
        }
        int prize = checkTicketPrize(ticket);
        calctotalPrizeMoney(ticket);
        calcEarnings();
        System.out.print("Prize Won: $" + prize + " Ticket: ");
        ticket.print();
    }
    //displays the total tickets,earnings,prizes won, and fundraising profit
    System.out.print("Number of tickets sold: " + numTicketsTotal + "\n");
    System.out.print("Total Earnings: $" + totalSales + "\n");
    System.out.print("Total prizes won: $" + prizeMoneyGiven + "\n");
    System.out.print("Total profit: $" + calcProfit() + "\n");
}

public static void main(String[] args) {
    LottoDraw lottoDraw = new LottoDraw(40);
    System.out.println("Full number list:");
    lottoDraw.madeLottoDraw.print();
    System.out.println();
    lottoDraw.selectWinningNums();
    System.out.print("Winning numbers:" + "\n");
    lottoDraw.winningNumbers.print();
    System.out.println();
    System.out.println("Tickets Bought");
    lottoDraw.generateLotteryTickets(100, 6);
    } 
}