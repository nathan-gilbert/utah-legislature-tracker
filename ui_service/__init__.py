from flask import Flask

ui_service = Flask(__name__)

from ui_service import routes
