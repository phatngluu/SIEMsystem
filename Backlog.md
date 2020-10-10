# Task 1
Backlogs:
| Role | Story | Estimation time (Day) | Priority |
|-|-|-|-|
|Developer|I have a dummy webserver so that I can use it as a target to monitor.| 2 | High |
| User | I can see potential security threats on my web server. | 2 | High |
| User | I can see detail of a security threat. | 0.5 | Low |
| User | I can see the alert ranging from low to high. |0.5 | Low |
| User | I can decide priority of the alert on my own. |0.5 | Low |
|||
|||

Implementation tasks:
- Create web server: https://www.vogella.com/tutorials/ApacheHTTP/article.html
- Basic of Apache Web Server: https://www.youtube.com/watch?v=rCr3-YlL5S8

# Task 2

Overall task is develop a SIEM system which:
- **monitors** the network connections of hosts and **alerts** users (network admin) when a port scan is detected.
- automatically prioritise the produces alerts in a range from low to high.

Example threats:
- Five failures of a connection within five minutes on a closed port from one IP address might come from a incorrect configured software which uses a wrong port number and can therefore have a low prioritisation. 
- On the other hand, 100++ connections within five minutes on different ports coming from different IP addresses are likely to be a distributed port scan coming from a bot net. This could be a high priority alert.

Implementation tasks:
- How can we simulate the port scanning? Found: `nmap` https://www.youtube.com/watch?v=4t4kBkMsDbQ
- How can determine the pattern for vertical, horizontal, block port scanning?

Backlogs:
|Role|Story|
|-|-|
|||
|||
|||
|||
|||

Questions:
- Do you have any suggestion about simulate a port scanning: vertical, horizontal, block scan?
- Event Hierarchy? For what, its uses? (Ex: previous SSH task)
- Pcap4j, how to capture network traffic?
- How to create a pattern?
