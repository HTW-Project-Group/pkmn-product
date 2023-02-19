export default class KeycloakHandler {
  private static _instance: KeycloakHandler;

  keycloakLoaded = false;

  static instance(): KeycloakHandler {
    if (!this._instance) {
      this._instance = new KeycloakHandler();
    }
    return this._instance;
  }

  onKeycloakLoaded(): Promise<boolean> {
    return new Promise((resolve, reject) => {
      const maxResponseTime = 5000;
      const checkInterval = 500;
      let passedTime = 0;
      const interval = setInterval(() => {
        if (this.keycloakLoaded) {
          resolve(this.keycloakLoaded);
        }
        passedTime += checkInterval;
      }, checkInterval);

      if (passedTime > maxResponseTime) {
        clearInterval(interval);
        reject(false);
      }
    });
  }

  finishKeycloakLoading(): void {
    this.keycloakLoaded = true;
  }
}
