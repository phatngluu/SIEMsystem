# Engine threshold values
- Portscan:
    
    PORTSCAN_V_MINIMUM_NUMBER_OF_PORTS=100
    PORTSCAN_H_MINIMUM_NUMBER_OF_HOSTS=3

# Set threshold value through engine instance
```java
engine.getCreatedInstance().setProperty("PORTSCAN_V_MINIMUM_NUMBER_OF_PORTS", "150");
engine.getCreatedInstance().getProperty("PORTSCAN_V_MINIMUM_NUMBER_OF_PORTS"));
```