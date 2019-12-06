package bg.sofia.uni.fmi.mjt.smartcity.hub;

import bg.sofia.uni.fmi.mjt.smartcity.device.SmartDevice;
import bg.sofia.uni.fmi.mjt.smartcity.enums.DeviceType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class SmartCityHub {

    private Set<SmartDevice> devicesByRegistrationTime = new LinkedHashSet<>(); // sorted by registration time
    //private Set<SmartDevice> devicesByPowerConsumption = new TreeSet<>(); // sorted by power

    public SmartCityHub() {

    }

    /**
     * Adds a @device to the SmartCityHub.
     *
     * @throws IllegalArgumentException         in case @device is null.
     * @throws DeviceAlreadyRegisteredException in case the @device is already registered.
     */
    public void register(SmartDevice device) throws DeviceAlreadyRegisteredException {
        if (device == null) {
            throw new IllegalArgumentException();
        }
        if (devicesByRegistrationTime.contains(device)) {
            throw new DeviceAlreadyRegisteredException();
        } else {
            devicesByRegistrationTime.add(device);
        }
    }

    /**
     * Removes the @device from the SmartCityHub.
     *
     * @throws IllegalArgumentException in case null is passed.
     * @throws DeviceNotFoundException  in case the @device is not found.
     */
    public void unregister(SmartDevice device) throws DeviceNotFoundException {
        if (device == null) {
            throw new IllegalArgumentException();
        }
        if (!devicesByRegistrationTime.contains(device)) {
            throw new DeviceNotFoundException();
        } else {
            devicesByRegistrationTime.remove(device);
        }
    }

    /**
     * Returns a SmartDevice with an ID @id.
     *
     * @throws IllegalArgumentException in case @id is null.
     * @throws DeviceNotFoundException  in case device with ID @id is not found.
     */
    public SmartDevice getDeviceById(String id) throws DeviceNotFoundException {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        for (SmartDevice i : devicesByRegistrationTime) {
            if (i.getId().equals(id)) {
                return i;
            }
        }
        throw new DeviceNotFoundException();
    }

    /**
     * Returns the total number of devices with type @type registered in SmartCityHub.
     *
     * @throws IllegalArgumentException in case @type is null.
     */
    public int getDeviceQuantityPerType(DeviceType type) {
        if (type == null) {
            throw new IllegalArgumentException();
        }
        int counter = 0;
        for (SmartDevice i : devicesByRegistrationTime) {
            if (i.getType() == type) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Returns a collection of IDs of the top @n devices which consumed
     * the most power from the time of their installation until now.
     * <p>
     * The total power consumption of a device is calculated by the hours elapsed
     * between the two LocalDateTime-s: the installation time and the current time (now)
     * multiplied by the stated nominal hourly power consumption of the device.
     * <p>
     * If @n exceeds the total number of devices, return all devices available sorted by the given criterion.
     *
     * @throws IllegalArgumentException in case @n is a negative number.
     */
    public Collection<String> getTopNDevicesByPowerConsumption(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        if (n > devicesByRegistrationTime.size()) {
            n = devicesByRegistrationTime.size();
        }
        LocalDateTime timeNow = LocalDateTime.now();
        List<String> topNDevices = new ArrayList<>();
        TreeSet<NameAndPowerConsumptionForPeriod> sortedDevicesByPowerConsumption = new TreeSet<>();

        for (SmartDevice i : devicesByRegistrationTime) {
            double timeElapsed = Duration.between(timeNow, i.getInstallationDateTime()).toHours();
            NameAndPowerConsumptionForPeriod device = new NameAndPowerConsumptionForPeriod(i.getPowerConsumption()*timeElapsed , i.getName());
            sortedDevicesByPowerConsumption.add(device);
        }
        Iterator<NameAndPowerConsumptionForPeriod> iterator = sortedDevicesByPowerConsumption.descendingIterator();
        for (int i = 0; i < n; i++) {
            topNDevices.add(iterator.next().getName());
        }
        return topNDevices;
    }

    /**
     * Returns a collection of the first @n registered devices, i.e the first @n that were added
     * in the SmartCityHub (registration != installation).
     * <p>
     * If @n exceeds the total number of devices, return all devices available sorted by the given criterion.
     *
     * @throws IllegalArgumentException in case @n is a negative number.
     */
    public Collection<SmartDevice> getFirstNDevicesByRegistration(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        if (n > devicesByRegistrationTime.size()) {
            n = devicesByRegistrationTime.size();
        }
        List<SmartDevice> answer = new ArrayList<>();
        Iterator p = devicesByRegistrationTime.iterator();
        for (int i = 0; i < n; i++) {
            answer.add((SmartDevice) p.next());
        }
        return answer;
    }
}
