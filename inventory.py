from openpyxl import load_workbook
from openpyxl.styles import Border, Side
from datetime import datetime

inventory_wb = load_workbook("a.xlsx", data_only=True)
inventory_ws = inventory_wb['Sheet']

order_wb = load_workbook("b.xlsx", data_only=True)
order_ws = order_wb["휴네스"]

def nvl(v):
    return "" if v is None else v

supplier = None
all_values = []

for idx, row in enumerate(inventory_ws.rows):

    # 첫째줄은 통과
    if idx == 0 or row[1].value == "N/A" or row[7].value == None:
       continue     

    # 업체별로 문서를 생성하기 위한 조건
    if (supplier != None) and (supplier != row[1].value):
        data = {"supplier": None}
        print("==========")
        # break

    supplier = nvl(row[1].value).strip()

    data = {}
    data["supplier"] = nvl(row[1].value).strip()
    data["inventory"] = nvl(row[4].value).strip()
    data["product"] = nvl(row[2].value[row[2].value.find(")")+1: len(row[2].value)]).strip()
    data["lowInventory"] = nvl(row[7].value).strip()

    all_values.append(data)
    
    # print(data["currentDate"] + " 업체: " + data["supplier"] + ", 재고단위: " + data["inventory"] + ", 제품: " + data["product"] + ", 부족재고: " + data["lowInventory"])

# print(all_values)
# print(len(all_values))

pdCount = len(all_values)

# 제품 수 만큼 행 추가
order_ws.insert_rows(13, pdCount)

for x in range(pdCount):
    # print("A"+str(13+x))
    # print(x)
    # print(all_values[x])

    order_ws["B3"] = datetime.today().strftime("%Y%m%d")
    order_ws["B4"] = datetime.today().strftime("%Y-%m-%d")
    order_ws["A9"] = all_values[x]["supplier"] + " 귀하"

    order_ws["A"+str(13+x)] = all_values[x]["supplier"]
    order_ws["B"+str(13+x)] = all_values[x]["product"]
    order_ws["C"+str(13+x)] = all_values[x]["inventory"]
    order_ws["D"+str(13+x)] = all_values[x]["lowInventory"]
    
    order_ws.row_dimensions[13+x].height = 25

    # 셀 스타일 적용
    for y in range(ord("a"), ord("g")+1):
        order_ws[chr(y)+str(13+x)].border = Border(top=Side(border_style='thin', color='000000'),
                                                   right=Side(border_style='thin', color='000000'),
                                                   bottom=Side(border_style='thin', color='000000'),
                                                   left=Side(border_style='thin', color='000000'))

order_ws.merge_cells("A13:A" + str(13 + pdCount-1))
order_wb.save(datetime.today().strftime("%Y%m%d") + "_" + order_ws["a9"].value.strip() + ".xlsx")
