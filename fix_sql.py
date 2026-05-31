import re

def main():
    path = 'database/SmartEng_SeedData.sql'
    with open(path, 'r', encoding='utf-8') as f:
        content = f.read()

    # Find string literals that have non-ASCII characters and prefix them with N
    # e.g., 'Hoàn thành' -> N'Hoàn thành'
    # Use negative lookbehind to avoid NN'
    new_content = re.sub(r"(?<!N)'([^']*[^\x00-\x7F]+[^']*)'", r"N'\1'", content)

    # Also, some values like JSON arrays might have non-ascii: '["Tạm biệt","Xin chào"]' -> N'...'
    with open(path, 'w', encoding='utf-8') as f:
        f.write(new_content)

if __name__ == '__main__':
    main()
