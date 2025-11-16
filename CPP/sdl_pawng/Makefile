CC=g++
CFLAGS=-O2 -g -Wall
LINKER=-lSDL -lSDL_net

all: dedicated
	$(CC) $(CFLAGS) src/client/Client.cpp -c -o src/client/Client.o
	$(CC) $(CFLAGS) src/client/Input.cpp -c -o src/client/Input.o
	$(CC) $(CFLAGS) src/client/Renderer.cpp -c -o src/client/Renderer.o
	$(CC) $(CFLAGS) src/sdl_pawng.cpp -c -o src/sdl_pawng.o
	$(CC) $(CFLAGS) $(LINKER) -lSDL_image -lSDL_ttf src/client/*.o src/common/*.o src/server/*.o src/sdl_pawng.o -o sdl_pawng
dedicated: init
	$(CC) $(CFLAGS) -D__DEDICATED__ src/sdl_pawng.cpp -c -o src/sdl_pawng.o
	$(CC) $(CFLAGS) $(LINKER) src/common/*.o src/server/*.o src/sdl_pawng.o -o sdl_pawngd
init:
	$(CC) $(CFLAGS) src/common/Console.cpp -c -o src/common/Console.o
	$(CC) $(CFLAGS) src/common/Game.cpp -c -o src/common/Game.o
	$(CC) $(CFLAGS) src/common/Entity.cpp -c -o src/common/Entity.o
	$(CC) $(CFLAGS) src/common/Timer.cpp -c -o src/common/Timer.o
	$(CC) $(CFLAGS) src/server/Server.cpp -c -o src/server/Server.o
clean:
	rm -fr src/client/*.o src/common/*.o src/server/*.o src/sdl_pawng.o sdl_pawng sdl_pawngd
