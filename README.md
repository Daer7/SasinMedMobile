# SasinMedMobile
A basic client app that interoperates with [SasinMed](https://github.com/janwawruszczak1998/SasinMed).

In order to see it work you need to:
1. run SasinMed via `flask run --host=0.0.0.0` on your machine so that the server is publicly available on your network,
2. either set your machine's IP statically to 192.168.1.6 or `FuelManager.instance.basePath` in `ShowQueueActivity.kt` to `"http://<whatever your machine's IP is>:5000"`.

You can now launch SasinMedMobile app as an AVD or install it on your smartphone (the app works provided your phone and the server are both on the same network).
