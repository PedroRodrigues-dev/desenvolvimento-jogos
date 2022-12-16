const canvas = document.querySelector("canvas");
const context = canvas.getContext("2d");

canvas.width = 240;
canvas.height = 160;

class Sprite {
  constructor(position) {
    this.position = position;
  }

  draw() {
    context.fillStyle = "red";
    context.fillRect(this.position.x, this.position.y, 10, 10);
  }

  update() {
    this.draw();
  }
}

class Player extends Sprite {
  speed = 5;

  constructor(position) {
    super(position);
    this.move();
  }

  move() {
    document.addEventListener("keydown", function (event) {
      switch (event.key) {
        case "ArrowUp":
          player.position.y -= player.speed;
          break;
        case "ArrowDown":
          player.position.y += player.speed;
          break;
        case "ArrowLeft":
          player.position.x -= player.speed;
          break;
        case "ArrowRight":
          player.position.x += player.speed;
          break;
      }
    });
  }
}

const player = new Player({
  x: canvas.width / 2 + 5,
  y: canvas.height / 2 + 5,
});

function animate() {
  window.requestAnimationFrame(animate);
  context.fillStyle = "black";
  context.fillRect(0, 0, canvas.width, canvas.height);
  player.update();
}

animate();
