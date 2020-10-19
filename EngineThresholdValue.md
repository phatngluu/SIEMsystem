# Engine threshold values
- Portscan:
    
    PORTSCAN_V_MINIMUM_NUMBER_OF_PORTS=100
    PORTSCAN_H_MINIMUM_NUMBER_OF_HOSTS=3

# Set threshold value through engine instance
```java
engine.getCreatedInstance().setProperty("PORTSCAN_V_MINIMUM_NUMBER_OF_PORTS", "150");
engine.getCreatedInstance().getProperty("PORTSCAN_V_MINIMUM_NUMBER_OF_PORTS"));
```

# Perform scenario port scan
## Vertical
sudo nmap -sT 192.168.0.103 -p1-155

## Horizontal
Machine 1: sudo nmap -sS 192.168.0.103 -p 22
Machine 2: sudo nmap -sS 192.168.0.103 -p 22
Machine 3: sudo nmap -sS 192.168.0.103 -p 22