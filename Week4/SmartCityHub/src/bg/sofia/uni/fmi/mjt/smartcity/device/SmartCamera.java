package bg.sofia.uni.fmi.mjt.smartcity.device;

import bg.sofia.uni.fmi.mjt.smartcity.enums.DeviceType;

import java.time.LocalDateTime;
import java.util.Objects;

public class SmartCamera implements SmartDevice {

    private static int getNumberForId = 0;

    String id = null;
    String name = null;
    double powerConsumption = 0;
    LocalDateTime installationDateTime = null;
    LocalDateTime registrationDateTime = null;
    DeviceType deviceType = DeviceType.CAMERA;

    public SmartCamera(String name, double powerConsumption, LocalDateTime installationDateTime) {
        this.name = name;
        this.powerConsumption = powerConsumption;
        this.installationDateTime = installationDateTime;
        registrationDateTime = LocalDateTime.now();
        this.id = getType().getShortName() + "-" + name + "-" + getNumberForId;
        getNumberForId++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SmartCamera)) return false;
        SmartCamera that = (SmartCamera) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof SmartDevice)) {
            throw new RuntimeException();
        }
        if(this.getPowerConsumption() > ((SmartDevice)o).getPowerConsumption()){
            return -1;
        }else{
            return 1;
        }
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPowerConsumption() {
        return powerConsumption;
    }

    @Override
    public LocalDateTime getInstallationDateTime() {
        return installationDateTime;
    }

    @Override
    public DeviceType getType() {
        return deviceType;
    }
}
