# "Java Concurrency in practice" recap #

## Sharing objects

1. visibility = there is no guarantee that the reading thread will see a value written by another thread

1.1. stale data = a thread may see an out-of-date data
1.2. nonatomic 64-bit operations = the JVM is permitted to treat a 64-bit read or write as two separate 32-bit operations
