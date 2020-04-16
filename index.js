const express = require("express");
const app = express();

const server = require("http").createServer(app);
const io = require("socket.io").listen(server);

const fs = require("fs");
server.listen(process.env.PORT || 3000);

const listUser = [];

io.socket.on("connection", (socket) => {
  console.log("connected");

  socket.on("user_login", (user_name) => {
    if (listUser.indexOf(user_name) > -1) {
      return;
    }
    listUser.push(user_name);
    socket.user = user_name;
  });

  socket.on("send_mess", (message) => {
    io.sockets.emit("receiver_mess", { data: socket.user + ": " + message });
  });
});
