Challenge: https://codingchallenges.substack.com/p/coding-challenge-48-data-privacy

### Resources:
- gRPC: https://www.baeldung.com/grpc-introduction
- gRPC enables the RPC calls and works over protobuf to work in a polyglot system.
-> Fnctionalities
- gRPC calls to the TokenizationService

### Libraries used
#### gRPC
1. grpc-netty: Transport implementation based on Netty.
2. grpc-probuf: API for gRPC over Protocol Buffers, including tools for serializing and de-serializing protobuf messages.
3. [grpc-stub](https://grpc.github.io/grpc-java/javadoc/io/grpc/stub/package-summary.html): There are two parts of grpc on server side: stub and call layer. Stub layer is a wrapper around the call layer. Stub layer is created with the help of `.proto` file, and functions can be overridden in the call layer.
4. protobuf-java: used by the xolstice plugin
#### Redis
5. jedis: client library for redis

### gRPC setup
Added gRPC libraries and added the gRPC binary to `/usr/local/bin` then added the `org.xolstice` plugin to create the lifecycle commands for maven.
[Source](https://www.xolstice.org/protobuf-maven-plugin/usage.html)
While creating a postman request, manually I had to import the `.proto` file. After that using the GRPCServer, I could hit the `tokenize` endpoint. Note that initially I did not use any security and requests were plain texts.

### Redis setup
I installed redis not redis stack. Redis stack offered a lot of tooling on top of redis, so I wanted to used redis as it is and in case I would need anything on top of it, I will build that too.
Resource: https://redis.io/docs/install/install-redis/install-redis-on-mac-os/
1. `brew install redis`
2. Start using the java redis client library: jedis

### Further Reading
- [Netty](https://netty.io/): Netty is an asynchronous event-driven network application framework
for rapid development of maintainable high performance protocol servers & clients.

### Further development
- GraphQL vs gRPC: fetching the required fields: FieldMasks in gRPC
- How stream work in gRPC