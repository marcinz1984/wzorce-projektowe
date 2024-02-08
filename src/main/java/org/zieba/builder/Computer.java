package org.zieba.builder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Computer {

    private String CPU;
    private String GPU;
    private Double RAM;
    private Double storage;
    private String motherboard;
    private String powerSupply;
    private String coolingSystem;
    private String operatingSystem;
    private Boolean isBluetoothEnabled;
    private Boolean isWifiEnabled;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String CPU;
        private String GPU;
        private Double RAM;
        private Double storage;
        private String motherboard;
        private String powerSupply;
        private String coolingSystem;
        private String operatingSystem;
        private Boolean isBluetoothEnabled;
        private Boolean isWifiEnabled;

        public Builder CPU(String CPU) {
            this.CPU = CPU;
            return this;
        }

        public Builder GPU(String GPU) {
            this.GPU = GPU;
            return this;
        }

        public Builder RAM(Double RAM) {
            this.RAM = RAM;
            return this;
        }

        public Builder storage(Double storage) {
            this.storage = storage;
            return this;
        }

        public Builder motherboard(String motherboard) {
            this.motherboard = motherboard;
            return this;
        }

        public Builder powerSupply(String powerSupply) {
            this.powerSupply = powerSupply;
            return this;
        }

        public Builder coolingSystem(String coolingSystem) {
            this.coolingSystem = coolingSystem;
            return this;
        }

        public Builder operatingSystem(String operatingSystem) {
            this.operatingSystem = operatingSystem;
            return this;
        }

        public Builder isBluetoothEnabled(Boolean isBluetoothEnabled) {
            this.isBluetoothEnabled = isBluetoothEnabled;
            return this;
        }

        public Builder isWifiEnabled(Boolean isWifiEnabled) {
            this.isWifiEnabled = isWifiEnabled;
            return this;
        }

        public Computer build() {
            return new Computer(CPU, GPU, RAM, storage, motherboard, powerSupply, coolingSystem, operatingSystem, isBluetoothEnabled, isWifiEnabled);
        }
    }
}
