# "Java Concurrency in practice" recap #

## Thread Safety

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


## Sharing Objects

##### Visibility

- there is no guarantee that the reading thread will see a value written by another thread
- stale data
    - a thread may see an out-of-date data
    - a thread may see an up-to-date data
    - can cause serious safety and liveness failures
    - is similar to READ_UNCOMMITTED isolation level
- nonatomic 64-bit operations
    - the JVM is permitted to treat a 64-bit read or write as two separate 32-bit operations
- locking and visibility
    - **locks on a common lock guarantee memory visibility**
- volatile variables
    - operations should not be reordered by the compiler and runtime
    - are not cached in registers or in caches
    - don't rely on volatile variables - it is more fragile and harder to understand\
    - use volatile variables when
        - writes to a variable do not depend on its current state
        - they don't participate in invariants with other variables
        - there is no other locking mechanisms or they are not required

##### Publishing and escape

- publishing - making an object available outside of its current scope
- escaping - an object has been published before it should have been


- safe construction practices
    - don't allow the this reference escape during construction
    - use a private constructor and a public factory method

   
- thread confinement
    - the technique to confine an object to a thread
    - an object is not necessarily should be thread-safe
    - but such usage is automatically thread-safe
    - ad hoc thread confinement
        - the responsibility of maintaining is up to the implementation
        - use standard language mechanisms (if they can help)
        - implement a particular subsystem as a single-threaded one
    - stack confinement
        - use local variables
        - make sure that local variables don't escape from the stack scope
    - LocalThread confinement
        - to associate an object with a thread
        - the methods to initialise/set/get an object
    
- use volatile to publish immutable objects
    - immutable objects provide some kind of atomicity
    - immutable objects illuminate race conditions
    - the assignment operator is an atomic one
- use immutable objects properly:
    - all fields are final
    - proper construction
    - unmodifiable state
    - final fields do not refer to mutable objects
- safe publication idioms
    - a properly constructed object can be safely published by:
        - a static initialiser (often is public as well)
        - a volatile field or an AtomicReference
        - a final field
        - a field that is properly guarded by a
    - JDK safe publications:
        - placing a key or value in a Hashtable, synchronizedMap, or ConcurrentMap
        - placing an element in a CopyOnWriteArrayList, CopyOnWriteArraySet, synchronizedList, or synchronizedSet
        - placing an element on a BlockingQueue or a ConcurrentLinkedQueue
    - effectively immutable objects:
        - their state will not be modified after publication
        - can be used without additional synchronization
    - mutable objects:
        - synchronisation mush be used not only to publish an object
        - synchronisation mush be used every time an object is accessed

##### Sharing objects safely

- **thread-confined** = a thread-confined object is **owned exclusively by and confined to one thread**, and can be modified by its owning thread. 
- **shared read-only** = **immutable and effectively immutable objects** can be accessed concurrently by multiple threads without additional synchronization, **but cannot be modified by any thread**.
- **shared thread-safe** = a thread-safe object performs synchronization **internally**, threads don't need synchronization through its public interface.
- **guarded** = a guarded object can be accessed only with **a specific lock held**.


## Composing Objects

- Designing a thread-safe class
- Instance confinement
- Delegating a thread-safety
- Additional functionality to existing thread-safe classes 
- documenting synchronization policies