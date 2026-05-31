import os
import glob

directories = [
    r'c:\Users\tinca\Downloads\CNPM\Code EngApp\src\view\study',
    r'c:\Users\tinca\Downloads\CNPM\Code EngApp\src\view\practice',
    r'c:\Users\tinca\Downloads\CNPM\Code EngApp\src\view\profile',
    r'c:\Users\tinca\Downloads\CNPM\Code EngApp\src\view\leaderboard',
    r'c:\Users\tinca\Downloads\CNPM\Code EngApp\src\view\admin'
]

for d in directories:
    if not os.path.exists(d): continue
    for filepath in glob.glob(os.path.join(d, '*.java')):
        with open(filepath, 'r', encoding='utf-8') as f:
            content = f.read()
        
        content = content.replace('extends JFrame', 'extends JPanel')
        content = content.replace('import javax.swing.JFrame;', 'import javax.swing.JPanel;')
        
        lines = content.split('\n')
        new_lines = []
        for line in lines:
            if 'super(' in line: continue
            if 'setSize(' in line: continue
            if 'setVisible(' in line: continue
            if 'setDefaultCloseOperation(' in line: continue
            if 'setLocationRelativeTo(' in line: continue
            if 'dispose()' in line: continue
            new_lines.append(line)
            
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write('\n'.join(new_lines))
