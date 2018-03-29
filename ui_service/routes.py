from flask import jsonify
from ui_service import ui_service

#TODO need a real landing page
@ui_service.route('/')
def index():
    return "Alright, let's do some politics"

@ui_service.route('/legislators')
def get_defect_types():
    return jsonify({})
