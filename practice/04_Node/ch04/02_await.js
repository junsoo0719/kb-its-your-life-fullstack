const fs = require('fs').promises;

async function readdirAsyn() {
  try {
    const files = await fs.readdir('./');
    console.log(files);
  } catch (err) {
    console.error(err);
  }
}

readdirAsyn();
