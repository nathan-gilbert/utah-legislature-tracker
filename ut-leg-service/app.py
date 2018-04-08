import connexion
from connexion.resolver import RestyResolver

if __name__ == '__main__':
    app = connexion.App(__name__, specification_dir='swagger/')
    app.add_api('ut-leg-service.yaml', resolver=RestyResolver('api'))
    app.run(port=9090)
