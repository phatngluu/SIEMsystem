#### 4.2. Portscan Collector

##### 4.2.1. Assumptions:

- TCP Connect(): open, closed port?
- Capture TCP packets
- Pcap4J, lib4cap, linux, granting JVM





##### 4.2.2. Implementation:

The Portscan Collector uses `Pcap4J` Java library to capture packets on a network interface. Please make sure your Java Virtual Machine has right to capture packets.

- Event Hierarchy
- Class representations (OpenPortscanEvent, ClosedPortscanEvent, PortscanEvent)
- 

