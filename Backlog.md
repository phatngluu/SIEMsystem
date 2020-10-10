### [Update your working progress](https://docs.google.com/spreadsheets/d/18XOCYiPlReWLvm8figp2Bqw22co1El8d6yE1n-eLEi8/edit?usp=sharing)
# Task 1
## Product Backlog:
|Number| Role | Story | Estimation time (Day) | Priority |
|-|-|-|-|-|
|1|Developer|I have a dummy webserver so that I can use it as a target to monitor.| 1 | High |
|2| User | I can see potential security threats on my web server. | 2 | High |
|3| User | I can see detail of a security threat. | 0.5 | Low |
|4| User | I can see the alert ranging from low to high. |0.5 | Low |
|5| User | I can decide priority of the alert on my own. |0.5 | Low |

Note:
- To create dummy web server, we decided to have buttons/URLs for generate log messages of: failed logins, access restricted resource... [See more vulnerabilities](https://owasp.org/www-project-top-ten/2017/A10_2017-Insufficient_Logging%2526Monitoring)
  - [Create web server](https://www.vogella.com/tutorials/ApacheHTTP/article.html)
  - [Basic of Apache Web Server](https://www.youtube.com/watch?v=rCr3-YlL5S8)

## Questions:
- [Martin] The webserver will be given or create by ourselves? If not given:
  - [Martin] Is the dummy web server above is valid?
- [Manuel & Team] Does Esper have alert ranking or just define the alert message is LOW/HIGH?
- [Team] Find: web server potential security threats? List them out.
- [Team] How we define the Event Hierarchy? How do we define patterns for recognizing the alert from events?

# Task 2
## Product Backlog:
|ID|Role|Story|Estimation time (day) | Priority |
|-|-|-|-|-|
|1|Developer|I have a networked host so that I can simulate a port scanning on it and use it as target to monitor.|1|High|
|2|User|I can see alerts when a port scan is detected.|2|High|
|3|User|I can see alerts ranking from low to high (SIEM system have to proritize alerts)|0.5|Low|

Implementation tasks:
- How can we simulate the port scanning? Found: `nmap` https://www.youtube.com/watch?v=4t4kBkMsDbQ
- Determine the pattern for vertical, horizontal, block port scanning?


Questions:
- [Manuel] Do you have any suggestion about simulate a port scanning: vertical, horizontal, block scan?
- [Team] Event Hierarchy? For what, its uses? (Ex: previous SSH task)
- [Team] Pcap4j, how to capture network traffic?
- [Team] How to create a pattern?

# Task 3

## Questions
- [Martin] What do you mean by at least 3 of out 4? Can we just only skip task 3? Is the score of 4 tasks done higher than 3 tasks?
# Task 4
## Product Backlog:
|ID|Role|Story|Estimation time (day) | Priority |
|-|-|-|-|-|
|1|User|I can see a list of recent and past alerts including the automatically assigned priority.||
|2|User|I can see number of processed events for each event type.||
|3|User|I can set options (required for configuration) of the SIEM system (e.g. thresholds).||