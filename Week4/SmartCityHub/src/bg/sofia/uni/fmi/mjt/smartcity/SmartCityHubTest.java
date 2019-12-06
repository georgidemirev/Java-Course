package bg.sofia.uni.fmi.mjt.smartcity;

import bg.sofia.uni.fmi.mjt.smartcity.device.SmartCamera;
import bg.sofia.uni.fmi.mjt.smartcity.device.SmartDevice;
import bg.sofia.uni.fmi.mjt.smartcity.device.SmartLamp;
import bg.sofia.uni.fmi.mjt.smartcity.device.SmartTrafficLight;
import bg.sofia.uni.fmi.mjt.smartcity.enums.DeviceType;
import bg.sofia.uni.fmi.mjt.smartcity.hub.DeviceAlreadyRegisteredException;
import bg.sofia.uni.fmi.mjt.smartcity.hub.DeviceNotFoundException;
import bg.sofia.uni.fmi.mjt.smartcity.hub.SmartCityHub;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Collection;

public class SmartCityHubTest {

    private static final String NAME_ONE = "asd";
    private static final String NAME_TWO = "qwe";
    private static final String NAME_THREE = "sd";
    private static final String CAMERA = "camera";
    private static final String LIGHT = "light";
    private static final String LAMP = "lamp";
    private static final String EXPECTED_ID = "CM-asd-0";

    private SmartCityHub hub;

    @Before
    public void init() {
        hub = new SmartCityHub();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRegisterNullDevice() throws DeviceAlreadyRegisteredException {
        hub.register(null);
    }

    @Test(expected = DeviceAlreadyRegisteredException.class)
    public void testRegisteringDuplicateDevice() throws DeviceAlreadyRegisteredException {
        SmartDevice device = new SmartCamera(NAME_ONE, 1, LocalDateTime.now());
        hub.register(device);
        hub.register(device);
    }

    @Test
    public void testSuccessfulRegister() throws DeviceAlreadyRegisteredException {
        SmartDevice device = new SmartCamera(NAME_ONE, 1, LocalDateTime.now());
        SmartDevice device1 = new SmartCamera(NAME_TWO, 1, LocalDateTime.now());
        hub.register(device);
        hub.register(device1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnregisterNullDevice() throws DeviceNotFoundException {
        hub.unregister(null);
    }

    @Test(expected = DeviceNotFoundException.class)
    public void testUnregisterAbsentDevice() throws DeviceNotFoundException, DeviceAlreadyRegisteredException {
        hub.register(new SmartCamera(NAME_ONE, 1, LocalDateTime.now()));
        hub.unregister(new SmartLamp(NAME_ONE, 1, LocalDateTime.now()));
    }

    @Test
    public void testSuccessfulUnregistering() throws DeviceAlreadyRegisteredException, DeviceNotFoundException {
        SmartDevice device = new SmartTrafficLight(NAME_TWO, 2, LocalDateTime.now());
        hub.register(device);
        hub.unregister(device);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByNullId() throws DeviceNotFoundException {
        hub.getDeviceById(null);
    }

    @Test(expected = DeviceNotFoundException.class)
    public void testNotFoundId() throws DeviceNotFoundException {
        hub.getDeviceById(NAME_ONE);
    }

    @Test
    public void testSuccessfulGet() throws DeviceAlreadyRegisteredException, DeviceNotFoundException {
        SmartDevice device = new SmartCamera(NAME_ONE, 1, LocalDateTime.now());
        String id = device.getId();
        hub.register(device);

        SmartDevice result = hub.getDeviceById(id);
        Assert.assertEquals(EXPECTED_ID, id);
        Assert.assertEquals(device.getId(), result.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDevicesWithNullType() {
        hub.getDeviceQuantityPerType(null);
    }

    @Test
    public void testGetDevicesByType() throws DeviceAlreadyRegisteredException {
        for (int i = 0; i < 10; i++) {
            hub.register(new SmartCamera(CAMERA, 2, LocalDateTime.now()));
        }
        for (int i = 0; i < 7; i++) {
            hub.register(new SmartTrafficLight(LIGHT, 1, LocalDateTime.now()));
        }
        for (int i = 0; i < 20; i++) {
            hub.register(new SmartLamp(LAMP, 3, LocalDateTime.now()));
        }

        int quantity = hub.getDeviceQuantityPerType(DeviceType.TRAFFIC_LIGHT);

        Assert.assertEquals(7, quantity);
    }

    @Test
    public void testNotTimeoutOnGetDevice() throws DeviceAlreadyRegisteredException {
        initializeBigData();

        hub.getDeviceQuantityPerType(DeviceType.CAMERA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeAmountOnGetConsumption() {
        hub.getTopNDevicesByPowerConsumption(-1);
    }

    @Test
    public void testNotTimeoutOnGetConsumption() throws DeviceAlreadyRegisteredException {
        initializeBigData();

        Collection<String> topNDevicesByPowerConsumption = hub.getTopNDevicesByPowerConsumption(1000000);

        Assert.assertEquals(topNDevicesByPowerConsumption.size(), 1000000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeAmountOnGetByRegisterTime() {
        hub.getFirstNDevicesByRegistration(-1);
    }

    @Test
    public void testNotTimeoutOnGetByRegistration() throws DeviceAlreadyRegisteredException {
        initializeBigData();

        Collection<SmartDevice> firstNDevicesByRegistration = hub.getFirstNDevicesByRegistration(1000000);

        Assert.assertEquals(firstNDevicesByRegistration.size(), 1000000);
    }

    private void initializeBigData() throws DeviceAlreadyRegisteredException {
        for (int i = 0; i < 1000000; i++) {
            hub.register(new SmartCamera(CAMERA, 2, LocalDateTime.now()));
        }
        for (int i = 0; i < 1000000; i++) {
            hub.register(new SmartTrafficLight(LIGHT, 1, LocalDateTime.now()));
        }
        for (int i = 0; i < 1000000; i++) {
            hub.register(new SmartLamp(LAMP, 3, LocalDateTime.now()));
        }
    }

}