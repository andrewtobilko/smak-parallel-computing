# "Java Concurrency in practice" recap #

## Thread safety

- concurrent programming is not about threads and locks
- writing thread-safe code is about access to state (shared, mutable) 
- locks and threads are just mechanisms to write such code


- state - state variables (instance or static ones)
- state - everything that can affect object's externally visible behaviour


- shared - could be accessed by multiple threads
- mutable - could change its state during its lifetime


- thread-safe is not about code
- thread-safe is about data and its protection

The program is broken if multiple threads access the same mutable state variable without appropriate synchronisation. There are three ways to fix it:

- don't share the state variable across multiple threads
- make the state variable immutable
- use synchronisation while accessing to the state variable


- it is far easier to design a class to be thread-safe than to retrofit it for thread safety later
- encapsulation, immutability, clean specification of invariants are mandatory techniques while designing thread-safe classes


- thread safety - correctness
- correctness - a conformation to the specification
    - _invariants_ - to constrain an object's state
    - _post-conditions_ - to describe the effects of object's operations
    
    
- **a class is thread-safe if it behaves correctly when accessed from multiple threads**
- **a thread-safe class encapsulates any needed synchronisation**
- **a thread-safe class does not require its clients to provide their synchronisation**

## Sharing objects

1. visibility = there is no guarantee that the reading thread will see a value written by another thread

1.1. stale data = a thread may see an out-of-date data
1.2. nonatomic 64-bit operations = the JVM is permitted to treat a 64-bit read or write as two separate 32-bit operations
