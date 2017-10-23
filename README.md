# "Java Concurrency in practice" recap #

## Thread safety

##### Introduction

- concurrent programming is not about threads and locks
- writing thread-safe code is about access to state (shared, mutable) 
- locks and threads are just mechanisms to write such code


- state - state variables (instance or static ones)
- state - everything that can affect object's externally visible behaviour


- shared - could be accessed by multiple threads
- mutable - could change its state during its lifetime


- thread-safe is not about code
- thread-safe is about data and its protection

##### "False" thread safety

The program is broken if multiple threads access the same mutable state variable without appropriate synchronisation. There are three ways to fix it:

- don't share the state variable across multiple threads
- make the state variable immutable
- use synchronisation while accessing to the state variable

##### Thread safety

- it is far easier to design a class to be thread-safe than to retrofit it for thread safety later
- encapsulation, immutability, clean specification of invariants are mandatory techniques while designing thread-safe classes


- thread safety - correctness
- correctness - a conformation to the specification
    - _invariants_ - to constrain an object's state
    - _post-conditions_ - to describe the effects of object's operations
    
##### Thread-safe class    

- a class is thread-safe if it behaves correctly when accessed from multiple threads
- a thread-safe class encapsulates any needed synchronisation
- a thread-safe class does not require its clients to provide their synchronisation

##### Stateless instances

- stateless object do not have a state
- stateless object's state is a particular computation saved into local variables that are stored on the thread's stack and are accessible only to the thread
- stateless object is always thread-safe

##### Atomicity

- an atomic operation is a single, indivisible operation
- a race condition is when the correctness of the application depends on the relative timing or interleaving of multiple threads
- compound operations:
    - check-then-act (lazy initialisation)
    - read-modify-write (the increment operator)
- compound operations should be atomic to avoid race conditions

##### Locks

- a build-in mechanism - intrinsic/monitor locks - synchronisation blocks
- locks make few compound operations atomic
- reentrancy
    - a thread can reenter to the monitor if it is currently holding it
    - the lock = an owning thread and an enquiring count
    - works per thread rather than per invocation basic (what POSIX thread mutex does)
    - simplifies OO concurrent code
- all multiple thread access to the same variable should be guarded by the same lock
- synchronisation causes liveness and performance issues

##### Liveness and performance

- narrow the scope of synchronisation blocks
- exclude long-running operations from synchronisation blocks
- don't mix up different synchronisation mechanisms
- synchronisation blocks should be "short enough" rather than "too small"


## Sharing objects

1. visibility = there is no guarantee that the reading thread will see a value written by another thread

1.1. stale data = a thread may see an out-of-date data
1.2. nonatomic 64-bit operations = the JVM is permitted to treat a 64-bit read or write as two separate 32-bit operations
