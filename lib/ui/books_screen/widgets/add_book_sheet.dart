import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:openreads/generated/locale_keys.g.dart';
import 'package:openreads/ui/books_screen/widgets/widgets.dart';

class AddBookSheet extends StatefulWidget {
  const AddBookSheet({
    super.key,
    required this.addManually,
    required this.searchInOpenLibrary,
    required this.scanBarcode,
  });

  final Function() addManually;
  final Function() searchInOpenLibrary;
  final Function() scanBarcode;

  @override
  State<AddBookSheet> createState() => _AddBookSheetState();
}

class _AddBookSheetState extends State<AddBookSheet> {
  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.end,
      mainAxisSize: MainAxisSize.min,
      children: [
        const SizedBox(height: 10),
        Container(
          height: 3,
          width: MediaQuery.of(context).size.width / 4,
          decoration: BoxDecoration(
            color: Colors.grey.shade300,
            borderRadius: BorderRadius.circular(10),
          ),
        ),
        Container(
          padding: const EdgeInsets.fromLTRB(10, 20, 10, 40),
          decoration: BoxDecoration(
            borderRadius: BorderRadius.circular(15),
          ),
          child: IntrinsicHeight(
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: [
                const SizedBox(width: 10),
                Expanded(
                  child: AddBookMethodButton(
                    text: LocaleKeys.add_manually.tr(),
                    icon: FontAwesomeIcons.solidKeyboard,
                    onPressed: widget.addManually,
                  ),
                ),
                const SizedBox(width: 20),
                Expanded(
                  child: AddBookMethodButton(
                    text: LocaleKeys.add_search.tr(),
                    icon: FontAwesomeIcons.magnifyingGlass,
                    onPressed: widget.searchInOpenLibrary,
                  ),
                ),
                const SizedBox(width: 20),
                Expanded(
                  child: AddBookMethodButton(
                    text: LocaleKeys.add_scan.tr(),
                    icon: FontAwesomeIcons.barcode,
                    onPressed: widget.scanBarcode,
                  ),
                ),
                const SizedBox(width: 10),
              ],
            ),
          ),
        ),
      ],
    );
  }
}
