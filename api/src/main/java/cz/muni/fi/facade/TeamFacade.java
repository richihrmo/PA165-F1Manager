package cz.muni.fi.facade;

/**
 * @author Matus Macko
 */
public interface TeamFacade {
    public List<OrderDTO> getAllOrders();
    public List<OrderDTO> getAllOrdersLastWeek(OrderState state);
    public List<OrderDTO> getOrdersByUser(Long userId);
    public List<OrderDTO> getOrdersByState(OrderState state);
    public OrderDTO getOrderById(Long id);
    public void shipOrder(Long id);
    public void finishOrder(Long id);
    public void cancelOrder(Long id);
    public OrderTotalPriceDTO getOrderTotalPrice(long id, Currency currency);
}
