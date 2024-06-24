
const express = require('express');
const app = express();
const port = 3000;
const destinosRouter = require('./controller/destinoController');

app.use(express.json());
app.use(destinosRouter);

app.listen(port, () => {
    console.log(`Server is running on port ${port}`);
});
