SEAD (Staged Event-Driven Architecture)
========

SEDA is an acronym for staged event-driven architecture, and decomposes a complex, event-driven application into a set of stages connected by queues.


## SEDA on Cassandra

[CASSANDRA-10989: Move away from SEDA to TPC](https://issues.apache.org/jira/browse/CASSANDRA-10989)

> As originally conceived, it means every request is split into several stages, and each stage is backed by a thread pool. That imposes certain challenges:
- thread parking/unparking overheads (partially improved by SEPExecutor in CASSANDRA-4718)
- extensive context switching (i-/d- caches thrashing)
- less than optimal multiple writer/multiple reader data structures for memtables, partitions, metrics, more
- hard to grok concurrent code
- large number of GC roots, longer TTSP
- increased complexity for moving data structures off java heap
- inability to easily balance writes/reads/compaction/flushing


## Reference

- http://www.eecs.harvard.edu/~mdw/papers/seda-sosp01.pdf
- http://matt-welsh.blogspot.jp/2010/07/retrospective-on-seda.html
