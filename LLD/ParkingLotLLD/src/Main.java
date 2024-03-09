import models.Bill;
import models.ParkingLot;
import models.Ticket;
import models.Vehicle;
import models.enums.PaymentType;
import models.enums.VehicleType;
import repository.*;
import service.*;
import controller.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        ParkingSpotRepository parkingSpotRepository = new ParkingSpotRepository();
        ParkingFloorRepository parkingFloorRepository = new ParkingFloorRepository();
        GateRepository gateRepository = new GateRepository();
        TicketRepository ticketRepository = new TicketRepository();
        BillRepository billRepository = new BillRepository();

        InitializationService initialisationService = new InitializationService(
                gateRepository,
                parkingLotRepository,
                parkingFloorRepository,
                parkingSpotRepository
        );

        TicketService ticketService = new TicketService(
                ticketRepository,
                parkingLotRepository,
                gateRepository);

        PaymentService paymentService = new PaymentService(billRepository);

        BillService billService = new BillService(billRepository, ticketRepository, gateRepository);
        TicketController ticketController = new TicketController(ticketService);
        BillController billController = new BillController(billService);
        InitController initController = new InitController(initialisationService);
        System.out.println("*** parking lot initialization start ***");
        ParkingLot parkingLot = initController.init();
        System.out.println("*** parking lot initialization end ***");

        Ticket ticket = null;
        while(true) {
            int option = sc.nextInt();
            if (option==1) {
                // park car
                Vehicle vehicle = new Vehicle("IND1234", VehicleType.EV);
                System.out.println("Generate ticket");
                ticket = ticketController.generateTicket(vehicle, parkingLot.getFloors().get(0).getEntry().getId(), parkingLot.getId());
                System.out.println("Ticket ID " + ticket.getId());
            }
            else if (option==2){
                // exit from park
                Bill bill = billController.generateBill(ticket.getId(), parkingLot.getFloors().get(0).getExit().getId());
                System.out.println("Bill .. " + bill.getAmount());

                System.out.println("Making payment");
                paymentService.payBill(bill, PaymentType.UPI);
            }
            else {
                break;
            }
        }
    }
}