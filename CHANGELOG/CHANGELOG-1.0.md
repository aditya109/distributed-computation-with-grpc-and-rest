# Changelog since v1.0

## Notable Changes

- Auto-scaling of gRPC-workers with both scale-up and scale-down functionality supported by `rest-server`.
- gRPC spawned port logging.
- Multi-threaded support for asynchronous functioning of `gRPC Client` within `rest-server`.
- Server configuration available within `rest-server`:
  - number of servers to be spawn
  - deadline in millisecond
  - deadline foot-printing delta
  