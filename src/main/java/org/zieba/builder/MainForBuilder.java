package org.zieba.builder;

public class MainForBuilder {

    public static void main(String[] args) {
        Computer windowsComputer = new Computer("Intel Core i7", "NVIDIA RTX 3080", 16.0, 512.0,
                "ASUS ROG Maximus", "Corsair 750W", "Liquid Cooling",
                "Windows 11", true, true);

        Computer linuxComputer = new Computer("AMD Ryzen 5", "AMD Radeon RX 580", 8.0, null,
                null, null, null,
                "Linux", null, null);

        Computer macComputer = new Computer("Mac M2 pro", null, 32.0, 256.0,
                null, null, null,
                "Mac OS", true, false);

        System.out.println(windowsComputer);
        System.out.println(linuxComputer);
        System.out.println(macComputer);

        Computer mac2 = Computer.builder()
                .CPU("M1")
                .RAM(24.0)
                .operatingSystem("Mac OS")
                .build();

        Computer pc2 = Computer.builder()
                .CPU("Amd Ryzen 5700x")
                .GPU("NVIDIA RTX 3070")
                .operatingSystem("Windows 10")
                .motherboard("Asus Rog")
                .isWifiEnabled(true)
                .build();

        System.out.println(mac2);
        System.out.println(pc2);
    }
}
