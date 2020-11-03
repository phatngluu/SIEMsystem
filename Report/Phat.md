### 3. System Overall Design

#### 3.1. Environment Setup:

##### 3.1.1. Overview:

The development environment is implemented as in figure xxx:

![Host machine](images/Host%20machine.png)

- **Raspberry Pi** – an 32bit operating system running on Raspberry Pi 4 – is the host machine. Other platform and operating system are also valid, more configurations are needed.
- **Apache Webserver** is the web server installed on the host machine. Please refer to section "3.1.2. Webserver" for installation guide.
- **Virtual network** is the network of 4 differrent virtual machines and runs inside the host machine. Please refer to section "3.1.3. Virtual network" for installation guide.
- **Resources information** is resources parameters (eg. CPU, RAM...) of the host machine.
- **SIEM system** is the system which monitors the Webserver, Virtual network and Resource information of the host machine.

##### 3.1.2. Apache Webserver:

##### 3.1.3. Virtual network:

To set up this virtual network, Docker is used since it provides a lightweight and minimum resources contamination. This section only focuses on set up virtual network on the host machine. For Docker installation please refer to https://phoenixnap.com/kb/docker-on-raspberry-pi.

Follow these steps to set up the virtual network. Open up Terminal and issue these commands:

1. Enter super user mode: 

   ```
   sudo su
   ```

2. Pull images from Docker Hub:

   ```
   docker pull ubuntu
   docker pull httpd
   docker pull redis
   ```

3. Create virtual machines from pulled images and start them:

   ```
   docker create --name vm2 httpd && docker start vm2 # Create and start VM2
   docker create --name vm3 redis && docker start vm3 # Create and start VM3
   docker run -it --name vm4 ubuntu /bin/bash # Create VM4
   exit # Exit created VM4 container
   docker run -it --name vm5 ubuntu /bin/bash # Create VM5
   exit # Exit created VM5 container
   docker start vm4 vm5 # Start VM4, VM5
   ```

4. To see the addresses of those virtual machines in the network, issue this command:

   ```
   docker network inspect bridge
   ```

5. Install the `nmap` tool for the attack machine `vm5`:

   ```
   docker exec -it vm5 /bin/bash # Enter vm5
   apt-get update
   apt-get install nmap -y # Install nmap
   ```

The SIEM system requires listening on the virtual network. Follow these steps to set up:

1. Install `lib4cap` as a library for capturing packets on the host machine.

   ```
   apt-get install libpcap-dev
   ```

2. Project requires dependency `Pcap4J` please make sure it is presented in the host machine.

3. Pcap4J needs administrator/root privileges. You can run Pcap4J with a non-root user by granting capabilities `CAP_NET_RAW` and `CAP_NET_ADMIN` to your java command by the following command:

   ```
   setcap cap_net_raw,cap_net_admin=eip /usr/lib/jvm/java-11-openjdk-armhf/bin/java
   ```

   Note: the path above may vary on differrent host machines.

##### 3.1.4. Resources information:

No set up is required for this.

##### 3.1.5. SIEM system:

The SIEM system requires JavaFX to run. Please make sure JavaFX 11 is installed. Download at https://gluonhq.com/products/javafx/. For development machine, `JavaFX armv6hf SDK` is chosen.

#### 3.2. System architecture:
