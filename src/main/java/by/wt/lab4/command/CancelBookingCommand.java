package by.wt.lab4.command;

import by.wt.lab4.constant.PageConstant;
import by.wt.lab4.constant.RequestConstant;
import by.wt.lab4.entity.Booking;
import by.wt.lab4.exception.ServiceException;
import by.wt.lab4.message.MessageHandler;
import by.wt.lab4.service.CommonService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CancelBookingCommand implements Command {

    private static Logger log = LogManager.getLogger("cancel booking");

    private CommonService commonService;

    public CancelBookingCommand(CommonService commonService) {
        this.commonService = commonService;
    }

    @Override
    public CommandResult execute(RequestContent requestContent) {
        CommandResult commandResult;
        int bookingId = Integer.parseInt(requestContent.getRequestParameter(RequestConstant.BOOKING_ID)[0]);
        System.out.println(bookingId);
        try {
            Booking booking = commonService.cancelBooking(bookingId);
        } catch (ServiceException e) {
            log.error("Error while canceling booking", e);
            return new DefaultCommand().execute(requestContent);
        }

        Map<String, Object> attributes = new HashMap<>();
        attributes.put(RequestConstant.SUCCESSFUL_BOOKING, MessageHandler.getMessage("message.successful_canceling",
                (String) requestContent.getSessionAttribute(RequestConstant.LOCALE)));
        commandResult = new CommandResult(CommandResult.ResponseType.FORWARD, PageConstant.BOOKINGS_PAGE, attributes);
        log.debug("Booking was successfully canceled");
        return commandResult;
    }
}
